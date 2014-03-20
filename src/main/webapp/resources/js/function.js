$(function(){
	var STYLE_COAL = "#000";//煤层
	var STYLE_MUD = "#996633";//泥岩
	var STYLE_ASH = "#A8A8A8";//灰岩
	
	var canvas = $('canvas');
	
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
		_clone.find("input").blur(renderCanvas);
		_td.append(_clone);
	});
	
	$(".glyphicon-minus").click(function() {
		$(this).parent().remove();
	});
	
	
	var renderCanvas = function() {
		initCanvas();
		//开始顶层
		var roofs = collectStratums($("#roof"));
		var x=50; var y=30;
		$(roofs).each(function(index, keypair) {
			var _height = keypair.value * 10;
			var _style = getStyle(keypair.id);
			canvas.drawRect({
				fillStyle: _style,
				fromCenter:false,
				x:x,
				y:y,
				width:100,
				height:_height
			});
			y = y + _height;
		});
		y = y+5;//开始掌子面
		var tunnels = collectStratums($("#tunnel"));
		$(tunnels).each(function(index, keypair) {
			var _height = keypair.value * 10;
			var _style = getStyle(keypair.id);
			canvas.drawRect({
				fillStyle: _style,
				fromCenter:false,
				x:x,y:y,
				width:100,
				height:_height
			});
			y = y + _height;
		});
		
		y = y+5;//开始底层
		var floors = collectStratums($("#floor"));
		$(floors).each(function(index, keypair) {
			var _height = keypair.value * 10;
			var _style = getStyle(keypair.id);
			canvas.drawRect({
				fillStyle: _style,
				fromCenter:false,
				x:x,y:y,
				width:100,
				height:_height
			});
			y = y + _height;
		});
	};
	
	var collectStratums = function(parent) {
		var _divs = parent.find("div");
		var result = new Array();
		for(var i = 0; i < _divs.size(); i++) {
			var _id = $(_divs[i]).find("select").prop("value");
			var _value = $(_divs[i]).find("input").prop("value");
			result.push({id:_id, value:_value});
		}
		return result;
	};
	
	var initCanvas = function() {
		canvas.clearCanvas();
		canvas.drawRect({
			fillStyle:STYLE_COAL,
			fromCenter:false,
			x:5,y:5,
			width:25,
			height:10
		}).drawText({
			fillStyle: '#000',
			fromCenter:false,
			strokeWidth: 1,
			x: 35, y: 5,
			fontSize: 6,
    		text: '煤层'
		}).drawRect({
			fillStyle:STYLE_MUD,
			fromCenter:false,
			x:70,y:5,
			width:25,
			height:10
		}).drawText({
			fillStyle: '#000',
			strokeWidth: 1,
			fromCenter:false,
			x: 100, y: 5,
			fontSize: 6,
    		text: '泥岩'
		}).drawRect({
			fillStyle:STYLE_ASH,
			fromCenter:false,
			x:135,y:5,
			width:25,
			height:10
		}).drawText({
			fillStyle: '#000',
			strokeWidth: 1,
			fromCenter:false,
			x: 165, y: 5,
			fontSize: 6,
    		text: '灰岩'
		});
	};
	
	var getStyle = function(id) {
		return id == 1 ? STYLE_COAL : (id == 2 ? STYLE_ASH : STYLE_MUD);
	};
	
	$(".canvas-sensitive").blur(renderCanvas);
	
	renderCanvas();
});
