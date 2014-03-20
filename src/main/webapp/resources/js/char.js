    function createChar(jsonData, bindDiv,minLen) {
        this.errorLog="";
        this.gebc = function (fatherId, tagName, className) {
            node = fatherId && document.getElementById(fatherId) || document;
            tagName = tagName || "*";
            className = className.split(" ");
            var classNameLength = className.length;
            for (var i = 0, j = classNameLength; i < j; i++) {
                //创建匹配类名的正则
                className[i] = new RegExp("(^|\\s)" + className[i].replace(/\-/g, "\\-") + "(\\s|$)");
            }
            var elements = node.getElementsByTagName(tagName);
            var result = [];
            for (var i = 0, j = elements.length, k = 0; i < j; i++) { //缓存length属性
                var element = elements[i];
                while (className[k++].test(element.className)) { //优化循环
                    if (k === classNameLength) {
                        result[result.length] = element;
                        break;
                    }
                }
                k = 0;
            }
            return result;
        }
        this.minLen = arguments[2]?arguments[2]:0.05;
        this.effect=null;
        this.dataIsFull = function (obj) {
            if (obj.position == "" || obj.name == "" || obj.bgimg == "" || obj.thickness == "") {
                return false;
            } else if (this.positionArr.toString().indexOf(obj.position) < 0) {
                return false;
            }
            return true;
        }
        this.dataOld = "";
        this.se = function (obj) {
            if (typeof (obj) == "string") {
                if (obj.indexOf("#") >= 0) {
                    obj = obj.replace("#", "");
                    if (document.getElementById(obj)) {
                        return document.getElementById(obj);
                    } else {
                        throw new Error("没有容器" + obj);
                    };
                } else if (obj.indexOf(".") >= 0) {
                    obj = obj.replace(".", "");
                    var retemp = this.gebc(document, "*", obj);
                    if (retemp.length > 0) {
                        return retemp;
                    } else {
                        throw new Error("没有容器" + obj);
                    };
                } else {
                    return document.createElement(obj);
                };
            } else {
                return obj;
            }
        };
        this.sumHeight = this.se(".char_histogram")[0].offsetHeight;
        this.getPercent = function (num, total) {
            num = parseFloat(num);
            total = parseFloat(total);
            if (isNaN(num) || isNaN(total)) {
                return "-";
            }
            return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
        }
        this.positionArr = new Array("顶板", "掌子面", "底板");
        this.heightSum = function () {
            var num = 0;
            for (var i = 0; i < jsonData.length; i++) {
                num += jsonData[i].thickness * 1;
            }
            return num;
        }
        this.box = this.se(bindDiv)[0];
        this.box.innerHTML="";

        this.clearChar = function () {
            this.se(bindDiv)[0].innerHTML = "";
        };
        this.init = function () {
            if(this.check()){
                this.dataOld=jsonData;
                $(".char3 span").shop!=undefined?$(".char3 span").shop():"";
                $(".char3").css("background","#fff");
                this.clearChar();
                this.createEleNew();
            }else{
                //alert(this.errorLog);
            }
        };
        this.createEle = function () {

            for (var i = jsonData.length - 1; i >= 0; i--) {
                var thisdiv = this.se("div");
                thisdiv.setAttribute("class", "char_tier");
                thisdiv.setAttribute("tier_id", jsonData[i].id);
                thisdiv.style.height = this.getPercent(jsonData[i].thickness, this.heightSum());
                for (var j = 1; j < 5; j++) {
                    var thisdivin = this.se("div");
                    thisdivin.setAttribute("class", "char" + j);
                    if (j == 1)
                        thisdivin.innerHTML = "<span>" + jsonData[i].position + "</span>";
                    if (j == 2)
                        thisdivin.innerHTML = "<span>" + jsonData[i].thickness + "</span>" + "<b>米</b>" + "<img src='../resources/img/iconbg.gif'/>";
                    if (j == 3) {
                        thisdivin.innerHTML = "<span class='bgimg' style='background-image:url(" + jsonData[i].bgimg + ");'></span>";
                    }
                    if (j == 4)
                        thisdivin.innerHTML = "<span>" + jsonData[i].name;
                    thisdiv.appendChild(thisdivin) + "</span>";
                }
                this.box.appendChild(thisdiv);
            }
        };
        this.createTier = function (data, tierh, sum) {
            if (this.dataIsFull(data)) {
                var finalResult = "<li style='height:" + this.getPercent(tierh.replace("%", "") / 100, sum.replace("%", "") / 100) + ";' tierh='" + tierh + "'>";
                for (var j = 1; j < 4; j++) {

                    if (j == 1) {
                        finalResult += "<div class='char2'><span>" + data.thickness + " 米</span>" + "<img src='../resources/img/iconbg.gif'/>" + "</div>";
                    }
                    if (j == 2) {
                        finalResult += "<div class='char3'><span class='bgimg' style='background-image:url(" + data.bgimg + ");'></span></div>";
                    }
                    if (j == 3)
                        finalResult += "<div class='char4'><span>" + data.name + "</span><img src='../resources/img/icontxt.gif'/></div>";

                }
                finalResult += "</li>"
                return finalResult;
            }
            return "";
        };
        this.createEleNew = function () {
            var thisdivp1 = "",
                thisdivp2 = "",
                thisdivp3 = "",
                p1Sum = 0,
                p2Sum = 0,
                p3Sum = 0;
            var tierHeights = this.restartHeight();
            for (var i = jsonData.length - 1; i >= 0; i--) {
                if (this.positionArr[0] == jsonData[i].position) {
                    p1Sum += tierHeights[i].replace("%", "") / 100;
                }
                if (this.positionArr[1] == jsonData[i].position) {
                    p2Sum += tierHeights[i].replace("%", "") / 100;
                }
                if (this.positionArr[2] == jsonData[i].position) {
                    p3Sum += tierHeights[i].replace("%", "") / 100;
                }
            }
            p1Sum = p1Sum * 100 + "%";
            p2Sum = p2Sum * 100 + "%";
            p3Sum = p3Sum * 100 + "%";
            for (var i = jsonData.length - 1; i >= 0; i--) {
                if (this.positionArr[0] == jsonData[i].position) {
                    thisdivp1 += this.createTier(jsonData[i], tierHeights[i], p1Sum);
                }
                if (this.positionArr[1] == jsonData[i].position) {
                    thisdivp2 += this.createTier(jsonData[i], tierHeights[i], p2Sum);
                }
                if (this.positionArr[2] == jsonData[i].position) {
                    thisdivp3 = this.createTier(jsonData[i], tierHeights[i], p3Sum) + thisdivp3;
                }
            }
            for (var i = 1; i <= 3; i++) {
                if (eval("thisdivp" + i) != "") {
                    var temp_rt = this.se("ul");
                    temp_rt.setAttribute("class", "char_rt fl");
                    temp_rt.innerHTML = eval("thisdivp" + i);
                    var temp = this.se("div");
                    temp.setAttribute("class", "char_position" + i);
                    var temp_lt = this.se("div");
                    temp_lt.setAttribute("class", "char1 fl");
                    temp_lt.innerHTML = "<span>" + this.positionArr[i - 1] + "</span>";
                    temp.appendChild(temp_lt);
                    temp.appendChild(temp_rt);
                    var clearFloat = this.se("div");
                    clearFloat.setAttribute("class", "clearfix");
                    temp.appendChild(clearFloat);
                    temp.style.height = eval("p" + i + "Sum");
                    this.box.appendChild(temp);
                    $(".char3 span").animate({"bottom":"1%"},500,function(){$(".char1,.char2,.char4").css("visibility","visible");$(".char3").css({"background":"#999"});});
                }
            }
        };
        this.restartHeight = function () {
            var tierHeights = new Array(),
                heightMinSum = 0,
                maxCount = 0;
            for (var i = 0; i < jsonData.length; i++) {
                if (this.dataIsFull(jsonData[i]))
                    tierHeights[tierHeights.length] = this.getPercent(jsonData[i].thickness, this.heightSum());
                else
                    tierHeights[tierHeights.length] = "0%";
            }
            for (var i = 0; i < jsonData.length; i++) {
                if (tierHeights[i].replace("%", "") / 100 <= this.minLen) {
                    heightMinSum += this.minLen - (tierHeights[i].replace("%", "") / 100);
                    tierHeights[i] = this.minLen * 100 + "%";
                    maxCount++;
                }
            }
            maxCount = jsonData.length - maxCount;
            for (var i = 0; i < jsonData.length; i++) {
                if (tierHeights[i].replace("%", "") / 100 > this.minLen) {
                    tierHeights[i] = (tierHeights[i].replace("%", "") / 100 - heightMinSum / maxCount) * 100 + "%";
                }
            }
            return tierHeights;
        };
        this.check = function () {
            //验证最小高度配置的合法性(全局)
            if (jsonData.length>0&&this.getPercent(1, jsonData.length).replace("%","")/100 < this.minLen){
                var normalCount=1/this.minLen;
                this.errorLog="当前地质层总数大于可渲染层数，请酌情调整最小高度或者删除地质层数！！maxcount:"+normalCount+",nowcount:"+jsonData.length;
                return false;
            }
            //验证数据是否存在
            if(jsonData.length<=0){
                this.errorLog="请添加数据！！";
                return false;
            }

            return true;
        };
    }