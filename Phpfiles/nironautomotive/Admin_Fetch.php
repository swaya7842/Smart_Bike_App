
<?php

require "connection.php";
 $Mobile_No=$_POST["Mobile_No"];

$sql="SELECT Full_Name, DOB, Email_Id, Mobile_No, Address, Organization, Designation FROM user_reg_vehicle_details WHERE Mobile_No='$Mobile_No'";

$result = $con ->prepare($sql);

    $result -> execute();
    $result->bind_result($Full_Name, $DOB, $Email_Id, $Mobile_No, $Address, $Organization, $Designation);

	$User_details = array();

	while ($result->fetch()) {
	$temp = array();
	$temp['Full_Name'] = $Full_Name;
	$temp['DOB'] = $DOB;
	$temp['Email_Id'] = $Email_Id;
	$temp['Mobile_No'] = $Mobile_No;
	$temp['Address'] = $Address;
	$temp['Organization'] = $Organization;
	$temp['Designation'] = $Designation;
	array_push($User_details , $temp);
	

}

echo json_encode($User_details);
 ?>