
 $(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });
});

function printName()  {
	  const name = document.getElementById('input_file').value;
	  document.getElementById("fileName").innerText = name;
}
