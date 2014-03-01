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
		_clone.find("select").prop("value", "");
		var _span = _clone.find("span");
		_span.removeAttr("glyphicon-plus");
		_span.addClass("glyphicon-minus");
		_span.click(function() {
			$(this).parent().remove();
		});
		var _td=$(this).parent().parent();
		var _rows = _td.find("div");
		var _select = $(_rows).find(":first-child");
		var _name = _select.prop("name");
		var _start = _name.substr(0, _name.indexOf("[")+1);
		var i = 0;
		for(; i < _rows.size(); i++) {
			 $(_rows[i]).find("select").prop("name", _start + i + "].stratumId");
			 $(_rows[i]).find("input").prop("name", _start + i + "].value");
		}
		_clone.find("select").prop("name", _start + i + "].stratumId");
		_clone.find("input").prop("name", _start + i + "].value");
		_td.append(_clone);
	});
	
	$(".glyphicon-minus").click(function() {
		$(this).parent().remove();
	});
	
	$('canvas').drawArc({
		  fillStyle: 'black',
		  x: 100, y: 100,
		  radius: 50
		});
});
