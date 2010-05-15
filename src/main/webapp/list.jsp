<%@ page import="common.UserList" %>
<%@ page import="common.User" %>

<% UserList list = (UserList) request.getAttribute("Users") ;%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title>SocialGrove</title>  
  	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="javascript/socialgrove.js"></script>  
  </head>
  <body>
	<div id="oTableContainer"></div>
			<!--table-->
				<!--tbody-->
						<script type="text/javascript">
						function fnInit()
						{	
						  // Declare variables and create the header, footer, and caption.
						  var oTable = document.createElement("TABLE");
						  var oTBody = document.createElement("TBODY");
						  var oRow, oCell1, oCell2, oCell3,oImg, blog;
						  var i, j;
						  var stock = new Array();
						  <%
							for(int i=0;i<list.count();i++){
								User user = list.getUser(i);
						  %>
						  	stock[<%=i%>] = new Array("<%=user.getImageURL()%>","<%=user.getFeedURL()%>");
						  <%
							}
						  %>
						  // Insert the created elements into oTable.
						  oTable.appendChild(oTBody);

						  // Set the table's border width and colors.
						  oTable.border=0;
						  oTable.bgColor="lightslategray";

						  // Insert rows and cells into bodies.
						  for (i=0; i<stock.length; i++)
						  {
							oTBody.appendChild(buildContent(i,stock[i][0],stock[i][1]));
						   }
						  // Set the background color of the first body.
						  oTBody.setAttribute("bgColor","lemonchiffon");

						  // Insert the table into the document tree.
						  document.getElementById("oTableContainer").appendChild(oTable);
						}
					</script>
					
	<script language="javascript">
		google.load("feeds", "1");
		google.setOnLoadCallback(fnInit);
	</script>
   </body>
</html>	