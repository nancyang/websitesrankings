function getWebsitesList() {
    var postData = new Object();
    postData.type = 'websiteslist';
 
    $.ajax({
        url: '../renderreport',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(postData),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            for (var i = 0; i < data.result.length; i++) {
            	$( '#website').append( '<option value="' + data.result[i] + '">' + data.result[i] + '</option>' );
            }
        },
        error:function(data,status,er) {
            alert('error: ' + data + ' status: ' + status + ' er:' + er);
        }
    });
    
}

function radioBtnClickEvent() {
	$('input:radio').click(
		function() {
			$('.criteria-div input, .criteria-div select').val('');
        	$('.criteria-div input, .criteria-div select').prop('disabled', true);
        	$('.easyui-datebox').datebox({
        		disabled:true
        	});
        	var id = this.id;
			$('#div-' + id + ' input, #div-' + id + ' select').prop('disabled', false);
			$('#div-' + id + ' .easyui-datebox').datebox({
		    	disabled:false,
		    	formatter: function(date){
		    		var y = date.getFullYear();
		    		var m = date.getMonth()+1;
		    		m = (m < 10 ? '0'+ m : m);
		    		var d = date.getDate();
		    		d = (d < 10 ? '0'+ d : d);
		    		return y + '-' + m + '-' + d;
		    	}
			});
	});
}

function submitBtnClickEvent() {
	$('#submitBtn').click(
		function() {
			var postData = new Object();
			postData.type = 'submit';
		    postData.criteria = $('input:radio[name=criteria]:checked').val();
		    postData.date = $('#date').datebox('getValue');
		    postData.todate = $('#todate').datebox('getValue');
		    postData.fromdate = $('#fromdate').datebox('getValue');
		    postData.website = $('#website').val();
			
		    $.ajax({
		        url: '../renderreport',
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(postData),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		        success: function (data) {
		        	$(".report-div").html('');
		            if (data.result.length == 0) {
		            	$(".report-div").append('<h1>No data found!</h1>');
		            }
		            else {
		            	var resultMsg = '', x = [], y = [];
		            	switch (postData.criteria) {
		            		case 'date'		: resultMsg = 'These are the top 5 ranking websites on ' + postData.date + '.';
		            						  for (var i = 0; i < data.result.length; i++) {
		            							  x.push(data.result[i].website);
		            							  y.push(data.result[i].visit);
		            						  }		            				          
		            					 	  break;
		            		case 'daterange': resultMsg = 'These are the top 5 ranking websites from ' + postData.fromdate +
		            						  ' to ' + postData.todate + '.';
						            		  for (var i = 0; i < data.result.length; i++) {
						            			  x.push(data.result[i].website);
						            			  y.push(data.result[i].visit);
						            		  }
       					 				 	  break;
		            		case 'website'	: resultMsg ='These are the top 5 ranking for ' + postData.website + '.';
		            						  for (var i = 0; i < data.result.length; i++) {
		            							  x.push(data.result[i].date);
		            							  y.push(data.result[i].visit);
		            						  }
		            						  break;
       					 	default			: break;			 
		            	}
		            	$(".report-div").append(resultMsg +'<div style="width:500px"><table id="reportTbl">' +
		            							'</table><br/><br/><div id="chartdiv"></div></div>');
		            	generateTable(data);
		            	generateChart(x, y);		                
		            }
		        },
		        error:function(data,status,er) {
		            alert('error: ' + data + ' status: ' + status + ' er:' + er);
		        }
		    });
	});
}

function generateTable(data) {
	$('#reportTbl').datagrid({
    	columns: [[
    	           {field:'date',title:'Date',width:120},
    	           {field:'website',title:'Website',width:220},
    	           {field:'visit',title:'No. of Visits',width:155,align:'right'}
    	          ]],
    	data: data.result
    });
}

function generateChart(xaxis, yaxis) {
     var plot = $.jqplot('chartdiv', [yaxis], {
        title: 'Websites Ranking',
         seriesDefaults:{
             renderer: $.jqplot.BarRenderer,
             rendererOptions: {
                barPadding: 1,
                barMargin: 15,
                barDirection: 'vertical',
                barWidth: 50
            }, 
            pointLabels: { show: true, location: 'e', edgeTolerance: -15 }
        },
        axesDefaults: {
            tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
            tickOptions: {
              angle: -30
            }
        },
        axes: {
            xaxis: {
            	renderer:  $.jqplot.CategoryAxisRenderer,
                ticks: xaxis
            },
            yaxis: {
            	min: 0,
            	max: yaxis[0]
            }
        },
        highlighter: {
            sizeAdjust: 7.5
        },
        cursor: {
            show: true
        }
    });
}