    function createChar(jsonData,bindDiv){
        this.gebc=function(fatherId,tagName,className){
            node = fatherId&&document.getElementById(fatherId) || document;
            tagName = tagName || "*";
            className = className.split(" ");
            var classNameLength = className.length;
            for(var i=0,j=classNameLength;i<j;i++){
                //创建匹配类名的正则
                className[i]= new RegExp("(^|\\s)" + className[i].replace(/\-/g, "\\-") + "(\\s|$)"); 
            }
            var elements = node.getElementsByTagName(tagName);
            var result = [];
            for(var i=0,j=elements.length,k=0;i<j;i++){//缓存length属性
                var element = elements[i];
                while(className[k++].test(element.className)){//优化循环
                    if(k === classNameLength){
                        result[result.length] = element;
                        break;
                    }	
                }
                k = 0;
            }
            return result;
        }
        this.se=function(obj){
            if(typeof(obj) == "string"){
                if (obj.indexOf("#")>=0) {
                    obj=obj.replace("#","");
                    if (document.getElementById(obj)) {
                        return document.getElementById(obj);
                    } else{
                        throw new Error("没有容器"+obj);
                    };
                }else if(obj.indexOf(".")>=0){
                    obj=obj.replace(".","");
                    var retemp = this.gebc(document,"*",obj);
                    if (retemp.length > 0) {
                        return retemp;
                    } else{
                        throw new Error("没有容器"+obj);
                    };
                } else{
                    return document.createElement(obj);
                };
            }else{
                return obj;
            }
	    };
        this.getPercent=function(num, total){
            num = parseFloat(num); 
            total = parseFloat(total); 
            if (isNaN(num) || isNaN(total)) { 
            return "-"; 
            } 
            return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%"); 
        }
        this.heightSum=function(){
            var num=0;
            for(var i=0;i<jsonData.length;i++){
                num+=jsonData[i].thickness;
            }
            return num;
        }
        this.box=this.se(bindDiv)[0];
        
        this.clearChar=function(){
            this.se(bindDiv)[0].innerHTML="";
        };
        this.init=function(){
            this.createEle();
        };
        this.createEle=function(){
            this.box.innerHTML="";
            for(var i=jsonData.length-1;i>=0;i--){
               var thisdiv=this.se("div");  
                thisdiv.setAttribute("class","char_tier");
                thisdiv.setAttribute("tier_id",jsonData[i].id);
                thisdiv.style.height=this.getPercent(jsonData[i].thickness,this.heightSum());
                for(var j=1;j<5;j++){
                    var thisdivin= this.se("div");
                    thisdivin.setAttribute("class","char"+j);
                    if(j==1)
                        thisdivin.innerHTML="<span>"+jsonData[i].position+"</span>";
                    if(j==2)
                        thisdivin.innerHTML="<span>"+jsonData[i].thickness+"</span>"+"<b>米</b>"+"<img src='../resources/img/iconbg.gif'/>";
                    if(j==3){
                        thisdivin.innerHTML="<span class='bgimg' style='background-image:url("+jsonData[i].bgimg+");'></span>";
                    }
                    if(j==4)
                        thisdivin.innerHTML="<span>"+jsonData[i].name;
                    thisdiv.appendChild(thisdivin)+"</span>";
                }
                this.box.appendChild(thisdiv);
            }
        };
        
        
    }