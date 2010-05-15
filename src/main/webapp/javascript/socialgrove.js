function buildContent(count,img, url){
	var row = document.createElement("tr");
	
	//Building the Image Table Content
	var imgTD = document.createElement("td");
	//imgTD.id = "img[+"i"+]";

	var image = document.createElement("img");
	image.src = img;
	image.height="61"; 
	image.width="70";
	imgTD.appendChild(image);
    row.appendChild(imgTD);

	// Building the Feed Content	
	var feedTD = document.createElement("td");
	var tweetTD = document.createElement("td");
	var feed = new google.feeds.Feed(url);
    feed.setResultFormat(google.feeds.Feed.JSON_FORMAT);
    feed.setNumEntries(1);
    feed.load(function(result) {
	  if (!result.error) {
	    for (var i = 0; i < 1; i++) {
	      var entry = result.feed.entries[i];
		  var div = document.createElement("div");
	      var linkDiv = document.createElement("div");
	      createLink(linkDiv, entry.link, entry.title);
	      createDiv(div, "body", entry.contentSnippet);
	      feedTD.appendChild(linkDiv);	
		  feedTD.appendChild(div);	
		  row.appendChild(feedTD);	
		  var tweetDiv = document.createElement("div");
		  //tweetDiv.class = "tweetmeme_button";
		  var tweetiframe = document.createElement("iframe");
		  tweetiframe.id="tweetmemeframe[j"+count+"]";
		 
		  //var formattedURL = urlFormatter(entry.link); 
		  //tweetiframe.src = urlFormatter(entry.link);  	
		  //tweetiframe.src = "http://api.tweetmeme.com/button.js?url="+entry.link;		
		  //tweetiframe.src = "http://api.tweetmeme.com/button.js?url="+entry.link;
		  //tweetDiv.style="clear:left;float: left; margin-right: 15px;margin-top:10px;margin-left:5px;";	
		  //alert( 
		  //select * from ('http://javarants.com/yql/javascript.xml') where 
		  //code='response.object = y.rest('+entry.link+').followRedirects(false).get().headers.location;');		
		  //alert(http://query.yahooapis.com/v1/public/yql?q=%0Ause%20'http%3A%2F%2Fjavarants.com%2Fyql%2Fjavascript.xml'%20as%20j%3B%20select%20*%20from%20j%20where%20code%3D'response.object%20%3D%20y.rest(%22http%3A%2F%2Ffeedproxy.google.com%2F~r%2FTechcrunch%2F~3%2FP-%255FqWQXyAPU%2F%22).followRedirects(false).get().headers.location%3B'&diagnostics=true);
		  //tweetiframe.src = "http://api.tweetmeme.com/button.js?url="+entry.link;
		  //tweetiframe.src = "http://api.tweetmeme.com/button.js?url="+
		  //"http://www.socialmediaexplorer.com/2010/04/19/search-engines-for-marketers/?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+SocialMediaExplorer+%28Social+Media+Explorer%29";
		  //alert(entry.link);  
		
		  tweetiframe.height="61"; 
		  tweetiframe.width="50";
		  tweetiframe.frameborder="0"; 
		  tweetiframe.scrolling="no"; 
		  tweetDiv.appendChild(tweetiframe);   
		  tweetTD.appendChild(tweetDiv);
		  var linkDiv = document.createElement("input");
		  linkDiv.id = "url["+count+"]";
		  linkDiv.type = "hidden";
		  linkDiv.value = entry.link; 	
		  tweetTD.appendChild(linkDiv); 
		  var urlDiv = document.createElement("script");
		  urlDiv.src=urlFormatter(entry.link,count);   	
		  tweetTD.appendChild(urlDiv); 
		  row.appendChild(tweetTD);	
		}	
	}
	});
	return row;
}

function createLink(parent, href, text){
	var link = createElement("a", parent, "", text);
	link.href = href;
	return link;
}

function createDiv(parent, className, opt_text){
	return createElement("div", parent, className, opt_text);
}

function urlFormatter(url,count){
	var enc_url = '"' + url +'"';
	var tablename = "j"+count;
	var YQL_url="http://query.yahooapis.com/v1/public/yql?q=";
	//var YQL_statement="use 'http://javarants.com/yql/javascript.xml' as "+tablename+j2;select * from j2 where code='response.object = y.rest("+enc_url+").followRedirects(false).get().headers.location;'"; 
	var YQL_statement1="use 'http://javarants.com/yql/javascript.xml' as "; 
	var YQL_statement2=" select * from ";
	var YQL_statement3=" where code='response.object = y.rest("+enc_url+").followRedirects(false).get().headers.location;'";
	var YQL_statement = encodeURIComponent(YQL_statement1)+tablename+encodeURIComponent(YQL_statement2)+tablename+encodeURIComponent(YQL_statement3); 
	//var YQL_script_url=YQL_url+encodeURIComponent(YQL_statement)+"&format=json&diagnostics=true&callback=urlHandler";
	var YQL_script_url=YQL_url+YQL_statement+"&format=json&diagnostics=true&callback=urlParser&urlHandler("+count+")";
	//alert(YQL_script_url);
	return YQL_script_url;
}

function urlHandler(cnt){
	alert(cnt);
}

function urlParser(o){
	var output = "";
	var cnt = foo();
	var tweetmeme = "http://api.tweetmeme.com/button.js?url=";
	if(!o.error){
		var item = o.query.results.result;
		//alert("item = "+item);
		//alert("cnt = "+cnt);
		if(item =='undefined')
			//alert(document.getElementById("url["+cnt+"]").value);
			output = tweetmeme +  document.getElementById("url["+cnt+"]").value;
			document.getElementById("tweetmemeframe[j"+cnt+"]").src = output;
		} 
		if(item !='undefined'){
			output = tweetmeme +  item;
			//alert(output);
			document.getElementById("tweetmemeframe[j"+cnt+"]").src = output;
		}
		//return; 	
}

function createElement(tagName, parent, className, opt_text){
	var div = document.createElement(tagName);
	div.className = className;
	parent.appendChild(div);
	if (opt_text) {
	    div.appendChild(document.createTextNode(opt_text));
	}
	return div;
}

function foo() {
    if( typeof foo.counter == 'undefined' ) {
        foo.counter = -1;
    }
    foo.counter++;
    return foo.counter;
}
