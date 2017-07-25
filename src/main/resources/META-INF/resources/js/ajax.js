function ajax_async_t(url,data,dataType,async){
	var rel;
	if(async==""||async==undefined){
		async=true;
	}else{
		async=false;
	}
	$.ajax({  		       
	    url: url,
	    type: "POST",
	    async:false,
	    dataType: dataType,
	    data: data,
	    success: function (ret) {
	    	if(ret.length==0){
//	    		toastr["warning"]("", "查询数据为空");
	    	}else{
	    		rel = ret;
	    	}
	    },
	    error: function (ret) { 
//	    	toastr["error"]("", "服务器异常或未查询到数据");
	    }  
	});
	return rel;
}
function GetRequest() { //截取URL的方法
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
    	  theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
      }
   }
   return theRequest;
}

function ajax_async_hou(url,data,dataType,async){
	var rel;
	if(async==""||async==undefined){
		async=true;
	}else{
		async=false;
	}
	$.ajax({  		       
	    url: url,
	    type: "POST",
	    async:false,
	    dataType: dataType,
	    data: data,
	    success: function (ret) {
	    	if(ret.length==0){
//	    		toastr["warning"]("", "查询数据为空");
	    	}else{
	    		rel = ret;
	    	}
	    },
	    error: function (ret) { 
	    	toastr["error"]("", "服务器连接异常");
	    }  
	});
	return rel;
}