/*
���ڴ����������ݵĺ���
Ϊ��ʹ���ű���IE��NS���������У���Ԫ�صĵ���ʹ��document.forms[0].test1��ʽ
������form.test1��ʽ
*/
function piliskys(num){
if(isNaN(num)) {
alert("����һ����Ч�����֣����������룡");
}
else creat(num);
}
function creat(num){
var money1 = new Number(num);
if(money1> 1000000000000000000) {
alert("�����������̫���������룡");
return;
}
var monee = Math.round(money1*100).toString(10)
var i,j;
j=0;
var leng = monee.length;
var monval="";
for( i=0;i<leng;i++)
{
monval= monval+to_upper(monee.charAt(i))+to_mon(leng-i-1);
}
repace_acc(monval);
}
function to_upper( a)
{
switch(a){
case '0' : return '��'; break;
case '1' : return 'Ҽ'; break;
case '2' : return '��'; break;
case '3' : return '��'; break;
case '4' : return '��'; break;
case '5' : return '��'; break;
case '6' : return '½'; break;
case '7' : return '��'; break;
case '8' : return '��'; break;
case '9' : return '��'; break;
default: return '' ;
}
}
function to_mon(a){
if(a>10){ a=a - 8;
return(to_mon(a));}
switch(a){
case 0 : return '��'; break;
case 1 : return '��'; break;
case 2 : return 'Ԫ'; break;
case 3 : return 'ʰ'; break;
case 4 : return '��'; break;
case 5 : return 'Ǫ'; break;
case 6 : return '��'; break;
case 7 : return 'ʰ'; break;
case 8 : return '��'; break;
case 9 : return 'Ǫ'; break;
case 10 : return '��'; break;
}
}
function repace_acc(Money){
Money=Money.replace("���","");
Money=Money.replace("���","��");
var yy;
var outmoney;
outmoney=Money;
yy=0;
while(true){
var lett= outmoney.length;
outmoney= outmoney.replace("��Ԫ","Ԫ");
outmoney= outmoney.replace("����","��");
outmoney= outmoney.replace("����","��");
outmoney= outmoney.replace("��Ǫ","��");
outmoney= outmoney.replace("���","��");
outmoney= outmoney.replace("����","��");
outmoney= outmoney.replace("��ʰ","��");
outmoney= outmoney.replace("����","����");
outmoney= outmoney.replace("��Ǫ","����");
outmoney= outmoney.replace("Ǫ��","Ǫ��");
yy= outmoney.length;
if(yy==lett) break;
}
yy = outmoney.length;
if ( outmoney.charAt(yy-1)=='��'){
outmoney=outmoney.substring(0,yy-1);
}
yy = outmoney.length;
if ( outmoney.charAt(yy-1)=='Ԫ'){
outmoney=outmoney +'��';
}
document.getElementById("outmoney").value=outmoney;
} 
function preview(oper)       
{
if (oper < 10)
{
bdhtml=window.document.body.innerHTML;//��ȡ��ǰҳ��html����
sprnstr='<!--startprint'+oper+'-->';//���ô�ӡ��ʼ����
eprnstr='<!--endprint'+oper+'-->';//���ô�ӡ��������
prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //�ӿ�ʼ�������ȡhtml

prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//�ӽ���������ǰȡhtml
window.document.body.innerHTML=prnhtml;
window.print();
window.document.body.innerHTML=bdhtml;
} else {
window.print();
}
}
//ֻ��������-use
function onlyNum(obj){
	if(obj.value!=obj.value.replace(/[^\u0030-\u0039\.]/g,''))
	obj.value=obj.value.replace(/[^\u0030-\u0039\.]/g,'');
}
//ֻ����Ӣ��,���Զ���Ӣ��ת��Ϊ��д-use
function onlyEngToUpperCase(obj){
	obj.value=obj.value.replace(/[^\u0041-\u007A]/g,'');
	obj.value=obj.value.toUpperCase();
}
//��������-use
function onlyName(obj){
	if(obj.value!=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,''))
		obj.value=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,'');
}
  
//������������
function noChinese(obj){
	if(obj.value!=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,''))
		obj.value=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,'');
}
/*
ȥ���ַ������ߵĿ��ַ�
*/
function trim(s)
{
	return trimRight(trimLeft(s))
}
/****************************************/
/* Trim the left blank of the string    */
/****************************************/
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="��" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
/*****************************************/
/* Trim the right blank of the string    */
/*****************************************/
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "��")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}

/**
�õ��ַ����ĳ��ȣ������ַ�����Ϊ2
*/
function getStrLength( str ) {
    num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig);
    if( arr != null )
        num += arr.length;
    return num;
}


  //���ַ����еĿ��˵�
function KillSpace(s){
    var s1="";
     if (isEmpty(s))//�ж��Ƿ�Ϊ��
     {	
	    return s;
     }else{//��Ϊ��
	    var len=s.length;
		for (var i=0;i<len;i++)
		{
		   if (!warnCharsInBag(s.charAt(i), " "))
		   {
		      s1=s1+s.charAt(i);
		   }
		}
	 }
	 return s1;
  }


/**
��ҳҳ���麯��
*/
function checkPageNO( pageNO, maxPageNO ) {
    if ( pageNO == "" ) {
        alert( "����дҳ�룡" );
        return false;
    } else if ( !isInteger( pageNO ) ) {
        alert( "����д��ҳ�벻��ȷ��" );
        return false;
    } else if ( parseInt( pageNO ) <= 0 ) {
        alert( "ҳ�벻��С��0��" );
        return false;
    } else if ( parseInt( pageNO ) > parseInt( maxPageNO ) ) {
        alert( "ҳ�벻�ܴ������ҳ�룡" );
        return false;
    }
    return true;
}


/**
 *�������б�ҳ����ʾ���������Ĺ̶�����showScrollHeader������ʾͷ�ű���showScrollFooter������ʾβ�ű�
 *���ߣ�����
 *ʱ�䣺200603018
 */
function showScrollHeader() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("<div align=\"center\"><div   align=\"center\"   style=\"overflow:auto;width:98%\">");
	} else {
		document.write("<table width=\"98%\"  align=\"center\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">	<tr>");
		document.write("<td valign=\"top\"  align=\"left\">");
		document.write("<div   align=\"center\"   style=\"overflow:auto;width:100%\">");
	}
}

function showScrollFooter() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("</div>	</div>");
	} else {
		document.write("</div>	</td>	</tr></table>");
	}
}
function findObj(theObj, theDoc){
    var p, i, foundObj;
    if(!theDoc) theDoc = document;  
    if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)  {
        theDoc = parent.frames[theObj.substring(p+1)].document;    
        theObj = theObj.substring(0,p);  
    }  
    if(!(foundObj = theDoc[theObj]) && theDoc.all) foundObj = theDoc.all[theObj];  
    for(i=0; !foundObj && i < theDoc.forms.length; i++)  foundObj = theDoc.forms[i][theObj];  
    for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++) foundObj = findObj(theObj,theDoc.layers[i].document);  
    if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj); return foundObj;
}
function addRow(tbName,innerHtmls){ //��ȡ���һ�е��кţ������txtTRLastIndex�ı�����
    var rowIndex = findObj(tbName+".index",document);
    var rowID = parseInt(rowIndex.value);
   
    var frame = findObj(tbName,document);
    //�����
    var newTR = frame.insertRow(frame.rows.length);
    newTR.id = tbName + "_tr_"+rowID;
    var newNameTD=newTR.insertCell(0);
	newNameTD.innerHTML = newTR.rowIndex.toString();
	var i = 0;j=1;
   	for(;i<innerHtmls.length;i++,j++){
	   	var newNameTD=newTR.insertCell(j);
	     //���������
	    newNameTD.innerHTML = innerHtmls[i];
   	}
    //�����:ɾ����ť
    var newDeleteTD=newTR.insertCell(j);
    //���������
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteRow('"+tbName+"_tr_" + rowID + "','"+tbName+"')\">ɾ��</a></div>";
   
    //���к��ƽ���һ��
    rowIndex.value = (rowID + 1).toString() ;
}
//ɾ��ָ����
function DeleteRow(rowid,tbName){
     var signFrame = findObj(tbName,document);
     var signItem = findObj(rowid,document);
    
     //��ȡ��Ҫɾ�����е�Index
     var rowIndex = signItem.rowIndex;
    
     //ɾ��ָ��Index����
     signFrame.deleteRow(rowIndex);
    
     // ����������ţ����û����ţ���һ��ʡ��
     for(i=rowIndex;i<signFrame.rows.length;i++){
      signFrame.rows[i].cells[0].innerHTML = i.toString();
     }
}        
