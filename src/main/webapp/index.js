const submitButton = document.getElementById('submit');
const accountInput = document.getElementById('account');
const passwordInput = document.getElementById('password');
const forgetPasswordButton = document.getElementById('forgetPassword');

// //test
// const forwardButton = document.getElementById('forwardTest');
// const includeButton = document.getElementById('includeTest');

// forwardButton.addEventListener('click', forward);
// includeButton.addEventListener('click', include);

forgetPasswordButton.addEventListener('click', TODO_Feature);
submitButton.addEventListener('click', submit);


function TODO_Feature() {
  alert("TODO feature ｡ﾟヽ(ﾟ´Д`)ﾉﾟ｡");
}

function submit() {
  console.log(accountInput.value + "-" + passwordInput.value);

  fetch("http://localhost:8080/login", {
    method: 'POST',
    headers: {
      'content-type': 'application/json; charset=utf-8'
    },
    body: JSON.stringify({
      account: accountInput.value,
      password: passwordInput.value
    })
  })
    .then(function (response) {
      //處理 response
      console.log('success');
      console.log(response);
      return response.text();
    }).then(function (text) {
      console.log(text);
      if (text === 'no') {
        popToast('Login Fail', 1000);
      }
    }).catch(function (err) {
      // Error :(
      popToast('Login Fail', 1000);
      console.log('fail');
    });

}

function popToast(content, delay = 1000) {
  var toast = document.createElement('div');
  var toastHtmlString = '<div class="toast-header">Toast Header</div><div class="toast-body">{content}</div>';
  toast.innerHTML = toastHtmlString.replace('{content}', content);
  toast.setAttribute('class', 'fade show');
  var body = document.getElementById('toast-area');
  body.appendChild(toast);
  setTimeout(() => {
    body.removeChild(toast);
  }, delay);
}

// function forward() {
//   console.log('forward test');

//   fetch("http://localhost:8080/forward", {
//     method: 'GET',
//     // headers: {
//     //   'content-type': 'application/json'
//     // },
//   })
//     .then(function (response) {
//       //處理 response
//       console.log('success');
//       console.log(response);
//       return response.text();
//     }).then(function (json) {
//       console.log(json);
//       // document.write(json);
//     }).catch(function (err) {
//       // Error :(
//       console.log('fail');
//     });

// }

// function include() {
//   console.log('include test');

//   fetch("http://localhost:8080/include", {
//     method: 'GET',
//     // headers: {
//     //   'content-type': 'application/json'
//     // },
//   })
//     .then(function (response) {
//       //處理 response
//       console.log('success');
//       console.log(response);
//       return response.text();
//     }).then(function (json) {
//       console.log(json);
//     }).catch(function (err) {
//       // Error :(
//       console.log('fail');
//     });

// }