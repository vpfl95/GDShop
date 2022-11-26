//로그인 (jquery)
$("#log_btn").click(function () {
  let id = $("#id").val();
  let pw = $("#pw").val();
  if (id == "") {
    $("#inp_id").text("빈칸을 입력해주세요.");
    $("#inp_id").attr("style", "display:block");
  } else if (pw == "") {
    $("#inp_id").attr("style", "display:none");
    $("#inp_pw").text("빈칸을 입력해주세요.");
    $("#inp_pw").attr("style", "display:block");
  } else {
    $.ajax({
      type: "POST",
      url: "/member/login",
      data: {
        id: id,
        pw: pw,
      },
      beforeSend: function (xhr) {
        //이거 안하면 403 error
        //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
        var $token = $("#token");
        xhr.setRequestHeader($token.data("token-name"), $token.val());
      },
      success: function (data) {
        if (data == 1) {
          location.href = "/";
        } else {
          if (data == 0) {
            $("#inp_pw").text("아이디 또는 비밀번호를 잘못 입력했습니다.");
            $("#inp_pw").attr("style", "display:block");
            $("#inp_id").attr("style", "display:none");
          } else {
            $("#inp_pw").attr("style", "display:none");
          }
        }
      },
    });
  }
});

//로그인 (jquery) - enter
$("#pw").on("keyup", function (key) {
  if (key.keyCode == 13) {
    let id = $("#id").val();
    let pw = $("#pw").val();

    if (id == "") {
      $("#inp_id").text("빈칸을 입력해주세요.");
      $("#inp_id").attr("style", "display:block");
    } else if (pw == "") {
      $("#inp_id").attr("style", "display:none");
      $("#inp_pw").text("빈칸을 입력해주세요.");
      $("#inp_pw").attr("style", "display:block");
    } else {
      $.ajax({
        type: "POST",
        url: "/member/login",
        data: {
          id: id,
          pw: pw,
        },
        beforeSend: function (xhr) {
          //이거 안하면 403 error
          //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
          var $token = $("#token");
          xhr.setRequestHeader($token.data("token-name"), $token.val());
        },
        success: function (data) {
          if (data == 1) {
            location.href = "/";
          } else {
            if (data == 0) {
              $("#inp_pw").text("아이디 또는 비밀번호를 잘못 입력했습니다.");
              $("#inp_pw").attr("style", "display:block");
              $("#inp_id").attr("style", "display:none");
            } else {
              $("#inp_pw").attr("style", "display:none");
            }
          }
        },
      });
    }
  }
});
