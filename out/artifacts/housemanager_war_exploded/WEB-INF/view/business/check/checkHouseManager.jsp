<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>房屋入库管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
			<div class="layui-inline">
			      <label class="layui-form-label">出租单号:</label>
			      <div class="layui-input-inline">
			        <input type="text" name="search_rentid"  id="search_rentid" autocomplete="off" class="layui-input">
			      </div>
		     </div>
			<div class="layui-inline">
		            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		     </div>
		 </div>
	</form>


	<div id="content" style="display: none;">
		<!-- 检查单位的表单 -->
		<blockquote class="layui-elem-quote" style="margin-top: 10px;">
			<h2>检查单表单</h2>
			<hr>
<form class="layui-form" method="post" id="checkFrm" lay-filter="checkFrm">
<div class="layui-form-item">
<div class="layui-inline">
<label class="layui-form-label">检查单号:</label>
<div class="layui-input-inline">
	<input type="text" name="checkid" readonly="readonly"
		placeholder="请输入检查单号" id="checkid" autocomplete="off"
		class="layui-input">
</div>
</div>
<div class="layui-inline">
<label class="layui-form-label">出租单号:</label>
<div class="layui-input-inline">
	<input type="text" name="rentid" readonly="readonly"
		placeholder="请输入出租单号" autocomplete="off" class="layui-input">
</div>
</div>
<div class="layui-inline">
<label class="layui-form-label">检查时间:</label>
<div class="layui-input-inline">
	<input type="text" name="checkdate" readonly="readonly"
		placeholder="请选择检查时间" id="checkdate" autocomplete="off"
		class="layui-input">
</div>
</div>
					<div class="layui-inline">
						<label class="layui-form-label">存在问题:</label>
						<div class="layui-input-inline">
							<input type="text" name="problem" lay-verify="required" placeholder="请输入出存在问题"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">赔付金额:</label>
						<div class="layui-input-inline">
							<input type="text" name="paymoney" placeholder="请输入赔付金额"
								lay-verify="required|number" value="0" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">操作员:</label>
						<div class="layui-input-inline">
							<input type="text" name="opername" placeholder="请输入操作员"
								readonly="readonly" autocomplete="off" class="layui-input">
						</div>
					</div>

				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">问题描述:</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入问题描述" lay-verify="required" name="checkdesc"
							class="layui-textarea"></textarea>
					</div>
				</div>
				<div class="layui-form-item" style="text-align: center;">
					<button type="button" lay-submit="" lay-filter="doSubmit"
						class="layui-btn layui-btn-normal  layui-icon layui-icon-release"
						id="doSubmit">保存</button>
				</div>
			</form>
		</blockquote>

		<!-- 出租单  房屋  客户的信息展开 -->
		<div style="padding: 10px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space8">
				<div class="layui-col-md4">
					<div class="layui-housed">
						<div class="layui-housed-header">房屋信息</div>
						<div class="layui-housed-body" id="house_housenumber">
						</div>
						<div class="layui-housed-body" id="house_housetype">
						</div>
						<div class="layui-housed-body" id="house_direction">
						</div>
						<div class="layui-housed-body" id="house_price">
						</div>
						<div class="layui-housed-body" id="house_rentprice">
						</div>
						<div class="layui-housed-body" id="house_deposit">
						</div>
						<div class="layui-housed-body" id="house_description">
						</div>
						<div class="layui-housed-body">
							房屋图片:<img alt="" width="200" height="150" id="house_houseimg" src="">
						</div>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="layui-housed">
						<div class="layui-housed-header">出租单信息</div>
						<div class="layui-housed-body" id="rent_rentid">
						</div>
						<div class="layui-housed-body" id="rent_price">
						</div>
						<div class="layui-housed-body" id="rent_begindate">
						</div>
						<div class="layui-housed-body" id="rent_returndate">
						</div>
						<div class="layui-housed-body" id="rent_opername">
						</div>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="layui-housed">
						<div class="layui-housed-header">客户信息</div>
						<div class="layui-housed-body" id="customer_identity">
						</div>
						<div class="layui-housed-body" id="customer_custname">
						</div>
						<div class="layui-housed-body" id="customer_sex">
						</div>
						<div class="layui-housed-body" id="customer_address">
						</div>
						<div class="layui-housed-body" id="customer_phone">
						</div>
						<div class="layui-housed-body" id="customer_houseeer">
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<script src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
		var tableIns;
		layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ],
				function() {
					var $ = layui.jquery;
					var layer = layui.layer;
					var form = layui.form;
					var table = layui.table;
					var laydate = layui.laydate;

					//根据出租单号查询
					$("#doSearch").click(function() {
						var rentid = $("#search_rentid").val();
						$.post("${ctx}/check/checkRentExist.action", {
							rentid : rentid
						}, function(obj) {
							if (obj === "") {
								layer.msg("您输入的出租单号不存在,请更正后再查询");
								$("#content").hide();
							} else {
								if (obj.rentflag == 1) {
									layer.msg("您输入的出租单相关房屋已经归还，无需再入库");
									$("#content").hide();
								} else {
									initCheckFormData(rentid);
									$("#content").show();
								}
							}

						})
					});
					
					//加载表单数据和 卡片面板的数据
					function initCheckFormData(rentid){
						$.post("${ctx}/check/initCheckFormData.action",{rentid:rentid},function(obj){
							//检查单
							var check=obj.check;
							form.val("checkFrm",check);
							//客户
							var customer=obj.customer;
							$("#customer_identity").html("身份证号: "+customer.identity);
							$("#customer_custname").html("客户姓名: "+customer.custname);
							$("#customer_sex").html("客户性别: "+(customer.sex==1?'男':'女'));
							$("#customer_address").html("客户地址: "+customer.address);
							$("#customer_phone").html("客户电话: "+customer.phone);
							$("#customer_houseeer").html("客户职位: "+customer.houseeer);
							
							//出租单
							var rent=obj.rent;
							
							$("#rent_rentid").html("出租单号: "+rent.rentid);	
							$("#rent_price").html("出租价格: "+rent.price);	
							$("#rent_begindate").html("起租时间: "+rent.begindate);	
							$("#rent_returndate").html("还房时间: "+rent.returndate);
							$("#rent_opername").html("操作员: "+rent.opername);	
							
							//房屋信息
							var house=obj.house;
							$("#house_housenumber").html("房屋号牌: "+house.housenumber);
							$("#house_housetype").html("房屋类型: "+house.housetype);
							$("#house_direction").html("房屋朝阳方向: "+house.direction);
							$("#house_price").html("购买价格: "+house.price);
							$("#house_rentprice").html("出租价格: "+house.rentprice);
							$("#house_deposit").html("出租押金: "+house.deposit);
							$("#house_description").html("房屋描述: "+house.description);
							$("#house_houseimg").attr("src","${ctx}/file/downloadShowFile.action?path="+house.houseimg);
						})
					}
					
					//保存
					form.on("submit(doSubmit)",function(){
						var params=$("#checkFrm").serialize();
						$.post("${ctx}/check/saveCheck.action",params,function(obj){
							layer.msg(obj.msg);
							$("#content").hide();
						})
						return false;
					});
				});
	</script>
</body>
</html>