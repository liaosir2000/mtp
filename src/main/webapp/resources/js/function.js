$(function(){
	$(".dictDelete").click(function(){
		var _self = $(this);
		var _id = _self.data('id');
		var _type = _self.data('type');
		$.ajax({url:_type+'/'+_id+'?delete', type:'POST', success:function(){
			window.location.reload(true);
		}});
		
	});
})
