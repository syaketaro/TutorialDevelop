package com.techacademy.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // 追加
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		
		model.addAttribute("userlist", service.getUserList());
		
		return "user/list";
	}
	
	@GetMapping("/register")
	public String getRegister(@ModelAttribute User user) {
		
		return "user/register";
	}
	
	 @PostMapping("/register")
	    public String postRegister(@Validated User user, BindingResult res, Model model) {
	        if(res.hasErrors()) {
	            // エラーあり
	            return getRegister(user);
	        }
	        // User登録
	        service.saveUser(user);
	        // 一覧画面にリダイレクト
	        return "redirect:/user/list";
	    }
	// User一覧画面から画面遷移した時とUser更新画面で入力チェックエラーがあった場合の遷移の違いがポイントになります。
	/* User一覧から遷移してきた場合は、                    URLのパスパラメータにユーザーのIDが設定されているので、そこから取得できると思いますが、
	 更新時にエラーが発生してUser更新画面を再表示する場合には  ユーザーのIDをUserクラスの中からIDを取得したいです。
	 今の状態だとエラー発生時にgetUserを呼び出していますが、IDがわかる情報を渡していないのではないでしょうか？ */
	@GetMapping("/update/{id}/")
	 public String getUser(User user, @PathVariable("id") Integer id, Model model) {
   //public String getUser(@PathVariable("id") Integer id, Model model) {	
        // Modelに登録
		if(id != null) {
			model.addAttribute("user", service.getUser(id));
		} 
		else if(id == null) {
			model.addAttribute("user", user);
		//第二引数で実行しているのがpostUserメソッドを呼び出し直している状態ですね。なので、正しくセットできていないと言えます。ヒントの部分をそのまま素直に記述してみるとどうなりそうでしょうかね？
		// ModelにはpostUser()から渡された引数のuserをセットする
			
		}
        // User更新画面に遷移
        return "user/update";
    }
	
	@PostMapping("/update/{id}/")
    public String postUser(Integer id, @Validated User user , BindingResult res, Model model) {
  //public String postUser(User user) {	
		if(res.hasErrors()) {
			
			return getUser(user, null, model);
		}
		// User登録
		service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
	
	@PostMapping(path="list", params="deleteRun")
	public String deleteRun(@RequestParam(name="idck")Set<Integer> idck, Model model) {
		service.deleteUser(idck);
		return "redirect:/user/list";
	}
}
