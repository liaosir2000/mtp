$(function(){
	$(".dictDelete").click(function(){
		var _self = $(this);
		var _id = _self.data('id');
		var _type = _self.data('type');
		$.ajax({url:_type+'/'+_id+'?delete', type:'POST', success:function(){
			window.location.reload(true);
		}});
		
	});
	
	$(".glyphicon-plus").click(function() {
		var _clone = $(this).parent().clone();
		_clone.find("input").prop("value", "");
		var _span = _clone.find("span");
		_span.removeAttr("glyphicon-plus");
		_span.addClass("glyphicon-minus");
		_span.click(function() {
			$(this).parent().remove();
		});
		$(this).parent().parent().append(_clone);
	});
	
	$(".glyphicon-minus").click(function() {
		$(this).parent().remove();
	});
});
