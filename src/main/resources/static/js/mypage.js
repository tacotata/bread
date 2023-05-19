 $('#checkPwd').click(function() {
        const checkPassword = $('#password').val();
        if(!checkPassword || checkPassword.trim() === ""){
            alert("비밀번호를 입력하세요.");
        } else{
            $.ajax({
                type: 'POST',
                url: '/member/api/v1/checkPwd',
                data: {'checkPassword': checkPassword},
                datatype: "text"
            }).done(function(result){
                console.log(result);
                if(result){
                    window.location.href="/member/modify";
                } else if(!result){
                    $(".error").text("비밀번호를 확인해주세요.");
                }
            }).fail(function(error){
                alert(JSON.stringify(error));
            })
        }
    });