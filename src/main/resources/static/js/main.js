//   sns 회원가입 모달
/*
    var snsType = [[${snsType}]];
    var num = [[${id}]];
    if(snsType != null && num != null){
        $(".modal").css("display","block");
    }
*/

 $(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });

       $('#changeStore-btn').click(function (e) {
            e.preventDefault();
            if (confirm("초기화(변경)시 장바구니 내 주문 내역은 자동 삭제되며, 첫 화면으로 이동됩니다.")) {
                   orders.delete();
                }
        });

});

var fileList ='';
var fileSize = '';
var ext = '';
const target = document.getElementById('input_file');
const fileName = document.getElementById("fileName");
var maxSize = 10 * 1024 * 1024; // 10MB

function printName(type) {
    for(i = 0; i < target.files.length; i++){
        fileSize = target.files[i].size;
        if(fileSize > maxSize){
                alert("첨부파일 사이즈는 각 10MB 이내로 등록 가능합니다.");
                return;
        }else if(type == "news" && $.inArray(target.files[i].name.split('.').pop().toLowerCase(), ['pdf', 'txt', 'xls', 'hwp', 'hwpx', 'xlsx', 'jpg' ,'jpeg', 'png', 'gif']) == -1){
                ext=target.files[i].name.split('.').pop().toLowerCase();
                alert(ext + "파일은 업로드 하실 수 없습니다.");
                return;
         }else if( (type == "news" || type=="contact") && target.files.length > 5 || Number($('#fileCnt').val()) + target.files.length >5){
                alert("파일은 5개 이하만 업로드 가능합니다.");
                return;
         }else if(type == "store" && $.inArray(target.files[i].name.split('.').pop().toLowerCase(), [ 'jpg' ,'jpeg', 'png']) == -1){
                ext=target.files[i].name.split('.').pop().toLowerCase();
                alert(ext + "파일은 업로드 하실 수 없습니다.");
                return;
         }else if((type == "item" || type=="contact") && $.inArray(target.files[i].name.split('.').pop().toLowerCase(), [ 'jpg' ,'jpeg', 'png', 'gif']) == -1){
                ext=target.files[i].name.split('.').pop().toLowerCase();
                alert(ext + "파일은 업로드 하실 수 없습니다.");
                return;
         }else{
            fileList += target.files[i].name + '<br>';
            fileName.innerHTML = fileList;
         }
    }
}

function newsValidation() {
    if($("#type option:selected").val() == "유형"){
        alert("유형을 선택해주세요.");
        return false;
    }else if($('#noticeSubject').val() ==""){
        alert("제목을 작성해주세요.");
        return false;
    }else if($('#message').val()=="" && $('#input_file').val()==""){
        alert("내용, 첨부사진 중 하나는 입력되어야합니다.");
        return false;
    }else{
        return true;
    }
}

function storeValidation() {

    if($("#name").val() == ""){
        alert("메장 이름을 작성해주세요.");
        return false;
    }else if($('#tel').val() ==""){
        alert("전화번호를 작성해주세요.");
        return false;
    }else if($('#address').val()=="" ){
        alert("주소를 작성해주세요.");
        return false;
    }else if($('#open').val()==""){
         alert("오픈시간을 작성해주세요.");
         return false;
    }else if($('#close').val()==""){
         alert("마감시간을 작성해주세요.");
         return false;
    }else if($('#startPickupTime').val()==""){
          alert("픽업 오픈시간을 작성해주세요.");
          return false;
    }else if($('#endPickupTime').val()==""){
          alert("픽업 마감시간을 작성해주세요.");
          return false;
    }else if($('#reserveNum').val()==""){
           alert("픽업시간당 예약팀 수를 작성해주세요.");
           return false;
    }else if($('#lastOrder').val()==""){
          alert("lastOrder를 작성해주세요.");
          return false;
    }else{
          return true;
    }
}

function snsUpdateValidation() {
     if($('#birthyear').val() =="" ||$('#birthmonth').val() =="" || $('#birthday').val() =="" ){
        alert("생년월일을 입력해주세요.");
        return false;
    }else if($('#mobile').val()==""){
         alert("핸드폰번호를 입력해주세요.");
         return false;
    }else if($('#birthyear').val().length  != 4 ){
         alert("생년월일을 확인해주세요.");
    }else if($('#birthmonth').val().length  != 2 || $('#birthday').val().length  != 2 ){
         alert("생년월일을 확인해주세요.");
    }
    else if($('#mobile').val() !=""){
       return phoneValid($('#mobile').val());
    }else{
         return true;
    }
}


function phoneValid(phone){
    var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    console.log(phone)
        if(!regPhone.test(phone)){
                alert("잘못된 번호입니다.");
                return false;
            }
            return true;
}


function isEmpty(str){
    if(typeof str == "undefined" || str == null || str == ""){
        return true;
    }else{
         return false;
    }

}

function datePickValidation(reservedTime) {
     if ((selectedDate <  start || selectedDate >  end )) {
             alert("예약할 수 없는 일자입니다. 다시 선택해주세요.");
             return false;
         }else if( isEmpty(time)){
             alert("예약 시간을 선택해주세요");
             return false;
         }else{
            if (confirm("30분 내 미결제 시 예약대기는 자동 취소 됩니다.")) {
             return true;
        }
    }
}

    function csValid(){
        if($("#csType option:selected").val() == "유형"){
            alert("유형을 선택해주세요.");
            return false;
        }else if($('#email').val() ==""){
                 alert("메일주소를 작성해주세요.");
                 return false;
        }else if($('#title').val() ==""){
            alert("제목을 작성해주세요.");
            return false;
        }else if($('#message').val()==""){
            alert("내용을 작성해주세요.");
            return false;
        }else{
            return true;
        }
    }

var main = {
    init : function(){
        var_this = this;
        $('#newsSaveBtn').on('click', function(){
            if(newsValidation()){
                if (confirm("등록하시겠습니까?")) {
                    main.save();
                }
            }
        });

        $('#btn-delete').on('click', function () {
             if (confirm("삭제하시겠습니까?")) {
                    main.delete();
             }
        });

        $('#btn-update').on('click', function () {
            if(newsValidation()){
                if (confirm("수정하시겠습니까?")) {
                    main.update();
                }
            }
        });

        $('.btn-fileDelete').on('click', function () {
            if (confirm("삭제하시겠습니까?")) {
               main.fileDelete( $(this).prev().val());
            }
        });
    },
    save  : function () {
        var data = {
            type : $("#type option:selected").val(),
            subject : $('#noticeSubject').val(),
            contents : $('#message').val(),
        };
        var form =$('#form')[0];
        var formData = new FormData(form);
        formData.append('file', $('#input_file'));
        formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

        $.ajax({
            type: 'POST',
            url: '/admin/api/v1/news',
            processData: false,
            contentType:false,
            data: formData,
        }).done(function(id) {
          $("#form")[0].reset();
          fileName.innerText ='';
          target.value ='';
            if(id == 0){
                alert('이미지 등록에 실패했습니다. 다시 시도해주세요.' +id );
                 location.reload();
            }else if(id == -1 || id== -2){
                alert('News 등록을 실패했습니다. 다시 시도해주세요.' +id );
                 location.reload();
            }else{
                alert('글이 등록되었습니다.' +id );
                 location.reload();
            }
        //window.location.href = '/';
        }).fail(function (error) {
            $("#form")[0].reset();
            fileName.innerText ='';
            target.value ='';
            alert(JSON.stringify(error));
        });
    },

     delete : function () {
            var id = $('#newsId').val();
            $.ajax({
                type: 'DELETE',
                url: '/news/api/v1/'+id,
                dataType:'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('글이 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },

        update : function () {
            var newsId =  $('#newsId').val()
            var data = {
                type : $("#type option:selected").val(),
                subject : $('#noticeSubject').val(),
                contents : $('#message').val(),
                fileCnt :  Number($('#fileCnt').val()) + target.files.length,
            };
            var form =$('#form')[0];
            var formData = new FormData(form);
            formData.append('file', $('#input_file'));
            formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

            $.ajax({
                type: 'PUT',
                url: '/admin/api/v1/news/'+newsId,
                processData: false,
                contentType:false,
                data: formData,
            }).done(function(id) {
                    alert('글이 수정되었습니다.' +id);
                    location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
                location.reload();
            });
        },

        fileDelete : function (fileId) {
                var fileCnt = Number($('#fileCnt').val()) -1;
                var data = {
                    id: $('#newsId').val(),
                    fileCnt: fileCnt,
                };
                $.ajax({
                    type: 'DELETE',
                    url: '/admin/api/v1/news/'+data.id+'/'+fileId,
                    dataType:'json',
                    contentType:'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    alert('파일이 삭제되었습니다.');
                    location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                    location.reload();
                });
            },
        };

main.init();



// sns 회원가입
var snsUser = {
    init : function () {
        var _this = this;
        $('#sns-join-btn').on('click', function () {
        if(snsUpdateValidation()){
            snsUser.update();
            }
        });
    },

    update : function () {
        var promotionAgree = 0;
        if($('#promotionAgree').is(':checked')){
            promotionAgree = 1;
        }

    if(snsType == 'Google'){
        var data = {
            birthyear: $('#birthyear').val(),
            birthmonth: $('#birthmonth').val(),
            birthday: $('#birthday').val(),
            mobile: $('#mobile').val(),
            promotionAgree: promotionAgree
        };
    }else{
        var data = {
            promotionAgree: promotionAgree
        };
    }
        $.ajax({
            type: 'PUT',
            url: '/sns-join/'+id,
            dataType: 'text',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (request,status,error) {
            alert(JSON.stringify("error: "+error +'request :' + request + 'status :' + status) +"실패");
        });
    },
};

snsUser.init();



//store
var store = {
    init : function(){
        var_this = this;
        $('#storeSaveBtn').on('click', function(){
            if(storeValidation()){
                if (confirm("등록하시겠습니까?")) {
                    store.save();
                }
            }
        });

        $('#store-btn-hide').on('click', function () {
             if (confirm("삭제하시겠습니까?")) {
                    store.hide();
             }
        });

        $('#btn-update').on('click', function () {
            if(storeValidation()){
                if (confirm("수정하시겠습니까?")) {
                    store.update();
                }
            }
        });
    },
    save  : function () {
        var data = {
            name : $("#name").val(),
            tel : $('#tel').val(),
            address : $('#address').val(),
            lastOrder : $('#lastOrder').val(),
            info : $('#info').val(),
            open : $('#open').val(),
            close : $('#close').val(),
            startPickupTime : $('#startPickupTime').val(),
            endPickupTime : $('#endPickupTime').val(),
            reserveNum : $('#reserveNum').val()
        };
        var form =$('#form')[0];
        var formData = new FormData(form);
        formData.append('file', $('#input_file'));
        formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

        $.ajax({
            type: 'POST',
            url: '/admin/api/v1/store',
            processData: false,
            contentType:false,
            data: formData,
        }).done(function(id) {
          $("#form")[0].reset();
          fileName.innerText ='';
          target.value ='';
          alert('글이 등록되었습니다.' +id );
          location.reload();
        //window.location.href = '/';
        }).fail(function (error) {
            $("#form")[0].reset();
            fileName.innerText ='';
            target.value ='';
            alert(JSON.stringify(error));
        });
    },

   hide : function () {
            var id = $('#storeId').val();

            var data = {
                name : $("#name").text(),
                tel : $('#tel').text(),
                address : $('#address').text(),
                lastOrder : $('#lastOrder').text(),
                info : $('#info').text(),
                hide_yn : 1,
                open : $('#open').val(),
                close : $('#close').val(),
                startPickupTime : $('#startPickupTime').val(),
                endPickupTime : $('#endPickupTime').val(),
                reserveNum : $('#reserveNum').val()
            }
            console.log(data)
            $.ajax({
                   type: 'PUT',
                    url: '/admin/store/api/v1/'+id,
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    alert('삭제되었습니다.');
                    window.location.href = '/';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
                },
        update : function () {
            var id =  $('#storeId').val()
            var fileId = $('#storeFileId').val()
            var data = {
               hideYn : $("#hideYn option:selected").val(),
               name : $("#name").val(),
               tel : $('#tel').val(),
               address : $('#address').val(),
               lastOrder : $('#lastOrder').val(),
               info : $('#info').val(),
               open : $('#open').val(),
               close : $('#close').val(),
               startPickupTime : $('#startPickupTime').val(),
               endPickupTime : $('#endPickupTime').val(),
               reserveNum : $('#reserveNum').val()

            };
            var form =$('#form')[0];
            var formData = new FormData(form);
            formData.append('file', $('#input_file'));
            formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

            $.ajax({
                type: 'PUT',
                url: '/admin/api/v1/store/'+id+'/'+fileId,
                processData: false,
                contentType:false,
                data: formData,
            }).done(function(id) {
                alert('글이 수정되었습니다.' +id);
                location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
                location.reload();
            });
        },
    };

store.init();


var item = {
    init : function(){
        var_this = this;
        $('#items-btn-hide').on('click', function () {
             if (confirm("아이템 상태를 변경하시겠습니까?")) {
                    item.hide();
             }
        });
    },
   hide : function () {
            var itemsId = $('#itemsId').val();
            var itemsStatus = $('#itemsStatus').val();
            itemsStatus == "SELL" ?  itemsStatus ="DISCON" : itemsStatus ="SELL"

          var data = {
             itemsId: itemsId,
             itemsStatus: itemsStatus
           }
            console.log(itemsStatus)
            $.ajax({
                   type: 'PUT',
                    url: '/admin/item/api/v1/'+data.itemsId+'?itemsStatus='+itemsStatus,
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function() {
                    alert('아이템 상태가 수정되었습니다.');
                    window.location.href = '/';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            },
        };

item.init();




var orders = {
    init : function(){
        $('.orderTimeBtn').on('click', function () {
            var_this = this;
           var time= $('input:radio[name="reservedTime"]:checked').val();
            console.log(time);
            console.log(selectedDate);
        if ((selectedDate <  start || selectedDate >  end )) {
             alert("예약할 수 없는 일자입니다. 다시 선택해주세요.");
             return false;
         }else if( isEmpty(time)){
             alert("예약 시간을 선택해주세요");
             return false;
         }else{
            if (confirm("30분 내 미결제 시 예약대기는 자동 취소 됩니다.")) {
                  orders.saveCart();
            }
         }
    });

        $('#orderBtn').on('click', function () {
                orders.order();
        });

        $('#menuPickDetailBtn').on('click', function () {
            orders.saveItem();
        });
    },
    saveCart  : function () {
         var data = {
             storeId : $('#storeId').val(),
             reservedTime : $('input:radio[name="reservedTime"]:checked').val(),
             reservedDate : selectedDate,
         };
        console.log(data);
        $.ajax({
               type: 'POST',
               url: '/order/api/v1/datePick/'+data.storeId,
               dataType: 'json',
               contentType:'application/json; charset=utf-8',
               data: JSON.stringify(data)
           }).done(function(cartId) {
               window.location.href ='/order/menu-pick/'+cartId ;
           }).fail(function (error) {
               alert(JSON.stringify(error));
           });
       },

       saveItem  : function () {
                var data = {
                    page : $('#page').val(),
                    cartId : $('#cartId').val(),
                    itemsId : $('#itemsId').val(),
                    count : $('#count').val()
                };
               console.log(data);
               $.ajax({
                      type: 'POST',
                      url: '/order/api/v1/cartItem/'+data.itemsId+'?page='+data.page+'&cartId='+data.cartId,
                      dataType: 'json',
                      contentType:'application/json; charset=utf-8',
                      data: JSON.stringify(data)
                  }).done(function() {
                       $('.cartItemModal').css('display', 'block');
                  }).fail(function (error) {
                      alert(JSON.stringify(error));
                  });
              },
        delete :  function () {
                 var cartId = $('#cartId').val();
                 $.ajax({
                     type: 'DELETE',
                     url: '/order/api/v1/cart/'+cartId,
                     dataType:'text',
                     contentType:'application/json; charset=utf-8'
                 }).done(function() {
                     window.location.href = '/order/store-pick/';
                 }).fail(function (error) {
                     alert(JSON.stringify(error));
                 });
             },

          order : function () {
                 var cartId = $('#cartId').val();
                 var dataList = new Array();
                 var paramData = new Object();
                 $("input[name=cartItemId]").each(function(index, item){
                    var cartItemId = $(this).val();
                    var data = new Object();
                    data["cartItemId"] = cartItemId;
                    dataList.push(data);
                });
                paramData['cartOrderDtoList'] = dataList;
                var param = JSON.stringify(paramData);
                console.log(param)
                 $.ajax({
                     type: 'POST',
                     url: '/order/api/v1/order/'+cartId,
                     dataType:'json',
                     cache : false,
                     contentType:'application/json',
                     data :param,
                 }).done(function() {
                     alert("주문이 완료됐습니다.")
                     window.location.href = '/order/list';
                 }).fail(function (request,status,error) {
                     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                 });
            },
        };

orders.init();


var cs = {
    init : function(){
    $('#contact-btn').on('click', function () {
            if(csValid()){
                if (confirm("답변은 작성하신 이메일로 발송됩니다.이메일 주소를 확인해주세요.")) {
                      cs.save();
               }
            }
        });

    },


    save  : function () {
        var data = {
            userId : $('#userId').val(),
            csType : $("#csType option:selected").val(),
            title : $('#title').val(),
            email : $('#email').val(),
            contents : $('#message').val(),
        };
        console.log(data);
        var form =$('#form')[0];
        var formData = new FormData(form);
        formData.append('file', $('#input_file'));
        formData.append('key', new Blob([JSON.stringify(data)] , {type: "application/json"}));

        $.ajax({
            type: 'POST',
            url: '/member/api/v1/contact',
            processData: false,
            contentType:false,
            data: formData,
        }).done(function(id) {
          $("#form")[0].reset();
          fileName.innerText ='';
          target.value ='';
            if(id > 0){
                alert('답변은 작성하신 이메일로 발송됩니다.');
                location.reload();
            }else{
                alert('다시 시도해주세요.');
                location.reload();
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
            $("#form")[0].reset();
            fileName.innerText ='';
            target.value ='';

        });
    },
}

cs.init();
