 //비밀번호 체크
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

    // 회원정보 수정 valid
    function userInfoValid(){
            if($('#name').val() ==""){
              alert("이름을 입력해주세요");
              return false;
            }else if($('#birthyear').val() =="" ||$('#birthmonth').val() =="" || $('#birthday').val() =="" ){
                alert("생년월일을 입력해주세요.");
                return false;
            }else if($('#mobile').val()==""){
                 alert("핸드폰번호를 입력해주세요.");
                 return false;
            }else if($('#birthyear').val().length  != 4 ){
                 alert("생년월일을 확인해주세요.");
                 return false;
            }else if($('#email').val() == '' || fn_emailChk($('#email').val())){
                 alert("이메일을 확인해주세요.");
                 return false;
            }else if($('#birthmonth').val().length  != 2 || $('#birthday').val().length  != 2 ){
                 alert("생년월일을 확인해주세요.");
            }
           else if($('#mobile').val().trim() !=""){
                 return phoneValid($('#mobile').val());
           }else{
              return true;
            }
    }

    //비밀번호 valid
    function pwdValid(){
        const nowPwd = $('#nowPwd').val();
        const newPw = $('#newPw').val();
        const newPwChk = $('#newPwChk').val();
        if(nowPwd.trim() === "" || newPw.trim() === "" || newPwChk.trim() === ""){
            alert("비밀번호를 입력하세요.");
            return false;
        }else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(newPw)){
             alert("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
             $('#password').focus();
             return false;
        }else if(newPw != newPwChk){
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }else if(nowPwd == newPw){
            alert("현재 비밀번호와 새로운 비밀번호가 같습니다.");
            return false;
        }else{
            return true;
        }
    }

    function withdrawValid(){
       if($('#password').val() == ""){
            alert("비밀번호를 작성해주세요.")
            return false;
          }else if($('#reason').val() == ""){
            alert("탈퇴 이유를 작성해주세요.")
            return false ;
          }else{
            return true;
          }
    }


    //이메일 체크
    function fn_emailChk(email){
        var regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
        if(!regExp.test(email)){
            return true;
        }
        return false;
    }

    //회원정보 수정
     function userUpdate(){

        var role = $("#role option:selected").val()
        if(role == "" ||  role == "null" || role == undefined){
            role ="GUEST"
        }
        var promotionAgree = false;
        if( $("input:checkbox[id='promotionAgree']").is(":checked") ){
            promotionAgree = true;
        }
            const data = {
                id: $('#userId').val(),
                name : $('#name').val(),
                birthyear: $('#birthyear').val(),
                birthmonth: $('#birthmonth').val(),
                birthday: $('#birthday').val(),
                email: $('#email').val(),
                role : role,
                promotionAgree : promotionAgree,
                storeAddress: $('#storeAddress').val(),
                storeName: $('#storeName').val(),
                storeTel: $('#storeTel').val(),
                team: $('#team').val(),
                teamTel: $('#teamTel').val(),
                mobile: $('#mobile').val(),
            };

            if(userInfoValid()){
                if (confirm("수정하시겠습니까?")) {
                   $.ajax({
                         type: 'PUT',
                         url: '/member/modify/'+data.id,
                         contentType: 'application/json; charset=utf-8',
                         data: JSON.stringify(data)
                     }).done(function(result){
                         if(result >0){
                            alert("회원 수정이 완료되었습니다.");
                            location.reload();
                         }else{
                             alert("이미 가입된 회웝입니다.");
                         }
                     }).fail(function(error){
                         alert(JSON.stringify(error));
                     });
                }
            }
        }

     //비밀번호 변경
      function pwdUpdate(){
             const id = $('#userId').val();
             const data = {
                     checkPassword: $('#nowPwd').val(),
                     password :  $('#newPw').val(),
             };
             if(pwdValid()){
                 if (confirm("수정하시겠습니까?")) {
                    $.ajax({
                          type: 'PUT',
                          url: '/member/api/v1/modify-pwd/'+id,
                          contentType: 'application/json; charset=utf-8',
                          data: JSON.stringify(data)
                      }).done(function(result){
                          if(result > 0){
                             alert("비밀번호가 변경됐습니다.");
                             window.location.href="/";
                          }else{
                              alert("비밀번호를 다시 확인해주세요.");
                          }
                      }).fail(function(error){
                          alert(JSON.stringify(error));
                      });
                 }
             }
     };

      //회원 탈퇴
   function withdrawSave(){
          const id = $('#userId').val();
          const data = {
              reason: $('#reason').val(),
              password :  $('#password').val(),
          };
          if(withdrawValid()){
              if (confirm("탈퇴하시겠습니까?")) {
                 $.ajax({
                       type: 'POST',
                       url: '/member/api/v1/withdraw/'+id,
                       contentType: 'application/json; charset=utf-8',
                       data: JSON.stringify(data)
                   }).done(function(result){
                       if(result > 0){
                          alert("그동안 감사했습니다. 탈퇴 완료됐습니다.");
                          window.location.href="/member/logout";
                       }else{
                           alert("비밀번호를 다시 확인해주세요.");
                       }
                   }).fail(function(error){
                       alert(JSON.stringify(error));
                   });
              }
        }
  };