function clickBtnDelete(){
    var idck;
    
    if (typeof document.frm.idck.length === 'undefined') {
        idck = [{ 'checked' : document.frm.idck.checked }]
    } else {
        idck = document.frm.idck;
    }
    
    var cnt = 0;
    for (i = 0; i < idck.length; i++) {
        if (idck[i].checked) {
            cnt++;
        }
    }
    
    if (cnt ==0) {
        alert('Userが選択されていません');
        return false; 
    }
    
    if(window.confirm(`${cnt}件削除して良いですか`))　{
        
        return true;
    } else {
        return false;
    }
    
}

document.getElementById("deleteRun").onclick = clickBtnDelete;
