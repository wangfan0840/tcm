/**
 * Created by lvcheng on 2017/4/18.
 */

$("#signIn").click(function() {
  loadIngFun();

});

function loadIngFun() {
  var name = $('#name').val();
 /* if (!name) {
    console.log('请填写用户名');
    swal({
      title: '请填写用户名!',
      type: 'error',
      timer: 1000,
      showConfirmButton: false
    });
    return;
  }
  var pas = $('#passwordIndex').val();
  if (!pas) {
    console.log('请填写密码');
    swal({
      title: '请填写密码!',
      type: 'error',
      timer: 1000,
      showConfirmButton: false
    });
    return;
  }*/

  // ajaxPost("/user/login",{
  //   userAccount:name,
  //   passWord:pas
  // }).success(function(res){
  //   if(res.data == 1){
  //
  //     $('#name').val('');
  //     $('#passwordIndex').val('');
  //     $('#verify').val('');
  //     window.location.href="home.html"
  //   }else{
  //     console.log(res.msg);
  //     swal({
  //       title: res.msg+'!',
  //       type: 'error',
  //       timer: 1000,
  //       showConfirmButton: false
  //     });
  //   }
  // });

  $('#name').val('');
  $('#passwordIndex').val('');
  $('#verify').val('');
  window.location.href = "special.html";
}



var parameterPar = {
  token: '',
  data: {}
};

function ajaxPost(url, parameter) {
  parameterPar.data = parameter;
  var p = JSON.stringify(parameterPar);
  return $.ajax('/ampc' + url, {
    contentType: "application/json",
    type: "POST",
    async: true,
    dataType: 'JSON',
    data: p
  })
}