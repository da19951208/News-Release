<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Type Table</title>
<link rel="stylesheet"
	href="https://www.jq22.com/demo/bootstrap-table-master20161026/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://www.jq22.com/demo/bootstrap-table-master20161026/src/bootstrap-table.css">
<link rel="stylesheet" href="docs.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/assets/bootstrap/js/bootstrap.min.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/docs/examples.js"></script>
<script
	src="https://www.jq22.com/demo/bootstrap-table-master20161026/src/bootstrap-table.js"></script>
<script src="docs.js"></script>
</head>
<body>

	<div>

		<div class="page-header">
			<h1 id="type-table">Type Table</h1>
		</div>

		<div>
			<label><input id="hover" type="checkbox" checked="">hover</label>
			<label><input id="striped" type="checkbox">striped</label>
		</div>

		<div class="bs-example">

			<table id="table-style" data-toggle="table"
				data-url="../TypeJsonServlet" data-sort-name="name"
				data-sort-order="desc" data-height="246" data-pagination="true">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="id" data-align="right" data-sortable="true">Type ID</th>
						<th data-field="name" data-align="center" data-sortable="true">Type Name</th>
						<th data-field="description" data-align="" data-sortable="true">Type Price</th>
					</tr>
				</thead>
			</table>

			<script>
				$(function() {
					$('#hover, #striped').click(
							function() {
								var classes = 'table';

								if ($('#hover').prop('checked')) {
									classes = 'table table-hover';
								}
								$('#table-style').bootstrapTable('destroy')
										.bootstrapTable(
												{
													classes : classes,
													striped : $('#striped')
															.prop('checked')
												});
							});
				});
			</script>

		</div>
	</div>

</body>
</html>