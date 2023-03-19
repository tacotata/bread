
 $(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });
});

var fileList ='';
var fileSize = '';
var ext = '';
const target = document.getElementById('input_file');
const fileName = document.getElementById("fileName");
var maxSize = 10 * 1024 * 1024; // 10MB

function printName() {
    for(i = 0; i < target.files.length; i++){
        fileSize = target.files[i].size;
        if(fileSize > maxSize){
                alert("첨부파일 사이즈는 각 10MB 이내로 등록 가능합니다.");
                return;
        }else if($.inArray(target.files[i].name.split('.').pop().toLowerCase(), ['pdf', 'txt', 'xls', 'hwp', 'hwpx', 'xlsx', 'jpg' ,'jpeg', 'png', 'gif']) == -1){
                ext=target.files[i].name.split('.').pop().toLowerCase();
                alert(ext + "파일은 업로드 하실 수 없습니다.");
                return;
         }else if(target.files.length > 5 || Number($('#fileCnt').val()) + target.files.length >5){
                alert("파일은 5개 이하만 업로드 가능합니다.");
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
            }else if(id == -1 || id== -2){
                alert('News 등록을 실패했습니다. 다시 시도해주세요.' +id );
            }else{
                alert('글이 등록되었습니다.' +id );
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