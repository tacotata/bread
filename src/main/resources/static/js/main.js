
 $(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });
});

function printName() {
    var fileList ='';
    const target = document.getElementById('input_file')
    for(i = 0; i < target.files.length; i++){
        fileList += target.files[i].name + '<br>';
    }
    if(target.files.length > 5){
        alert("파일은 5개 이하만 업로드 가능합니다.");
        fileList.value = "";
    }else{
        document.getElementById("fileName").innerHTML = fileList;
    }
}


var main = {
    init : function(){
        var_this = this;
        $('#newsSaveBtn').on('click', function(){
            var $fileUpload = $("input[type='file']");
            if (parseInt($fileUpload.get(0).files.length)>5){
            alert("파일은 5개 이하만 업로드 가능합니다.");
        }
        main.save();
        })
    },
    save  : function () {
        var data = {
            type : $("#type option:selected").val(),
            subject : $('#noticeSubject').val(),
            contents : $('#message').val(),
            delYn : 'N'
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
            if(id == 0){
                alert('이미지 등록에 실패했습니다. 다시 시도해주세요.' +id );
            }else if(id == -1 || id== -2){
                alert('News 등록을 실패했습니다. 다시 시도해주세요.' +id );
            }else{
                alert('글이 등록되었습니다.' +id );
            }
        //window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();