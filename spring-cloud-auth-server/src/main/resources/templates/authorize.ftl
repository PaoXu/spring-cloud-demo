<html>
<head>
<link rel="stylesheet" href="../css/wro.css"/>
</head>
<body>
	<div class="container">
		<h2>Please Confirm</h2>

		<p>
			Do you authorize "${authorizationRequest.clientId}" at "${authorizationRequest.redirectUri}" to access your protected resources
			with scope ${authorizationRequest.scope?join(", ")}.
		</p>
		<form id="confirmationForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="true" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Approve</button>
		</form>
		<form id="denyForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="false" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Deny</button>
		</form>
	</div>
	<script src="../js/wro.js"	type="text/javascript"></script>
</body>
</html>

<!--
<html><body><h1>OAuth Approval</h1><p>Do you authorize 'acme' to access your protected resources?</p>
<form id='confirmationForm' name='confirmationForm' action='/uaa/oauth/authorize' method='post'><input name='user_oauth_approval' value='true' type='hidden'/><ul><li><div class='form-group'>scope.openid: <input type='radio' name='scope.openid' value='true'>Approve</input> <input type='radio' name='scope.openid' value='false' checked>Deny</input></div></li></ul><label><input name='authorize' value='Authorize' type='submit'/></label></form></body></html>
-->
