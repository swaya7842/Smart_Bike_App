
<?php

require "connection.php";
 $Mobile_No=$_POST["Mobile_No"];

$sql="SELECT Full_Name, Owner_Id, DOB, Email_Id, Mobile_No, Address, Model, Vehicle_Identification_No, DOP, Vehicle_Reg_No FROM user_reg_vehicle_details WHERE Mobile_No='$Mobile_No'";

$result = $con ->prepare($sql);

    $result -> execute();
    $result->bind_result($Full_Name, $Owner_Id, $DOB, $Email_Id, $Mobile_No, $Address, $Model, $Vehicle_Identification_No, $DOP, $Vehicle_Reg_No);

	$User_details = array();

	while ($result->fetch()) {
	$temp = array();
	$temp['Full_Name'] = $Full_Name;
	$temp['Owner_Id'] = $Owner_Id;
	$temp['DOB'] = $DOB;
	$temp['Email_Id'] = $Email_Id;
	$temp['Mobile_No'] = $Mobile_No;
	$temp['Address'] = $Address;
	$temp['Model'] = $Model;
	$temp['Vehicle_Identification_No'] = $Vehicle_Identification_No;
	$temp['DOP'] = $DOP;
	$temp['Vehicle_Reg_No'] = $Vehicle_Reg_No;
	array_push($User_details , $temp);
	 echo json_encode($User_details);
	// $status = "Success" ;
}
// echo json_encode(array("response"=>$status,"name"=>$name));
 // echo json_encode(array("Response"=>$status,"Data"=>$User_details));

 ?>