/**
 * Created by wangfan on 2017/7/23.
 */

var parameterPar = {
    token: '',
    data: {}
};
function ajaxPost(url, parameter) {
    parameterPar.data = parameter;
    var p = JSON.stringify(parameterPar);
    return $.ajax('/tcm' + url, {
        contentType: "application/json",
        type: "POST",
        async: true,
        dataType: 'JSON',
        data: p
    })
}
function ajaxGet(url, parameter) {
    return $.ajax('/tcm' + url, {
        type: "GET",
        async: true,
        data: parameter
    })
}
