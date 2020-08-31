<?php

require_once 'connection.php';

 // $json = file_get_contents('php://input');
 // $data = json_decode($json, true);

$Full_Name=$_POST["Full_Name"];
$Owner_Id=$_POST["Owner_Id"];
$DOB=$_POST["DOB"];
$Email_Id=$_POST["Email_Id"];
$Mobile_No=$_POST["Mobile_No"];
$Address=$_POST["Address"];
$Model=$_POST["Model"];
$Vehicle_Identification_No=$_POST["Vehicle_Identification_No"];
$DOP=$_POST["DOP"];
$Vehicle_Reg_No=$_POST["Vehicle_Reg_No"];
$Password=$_POST["Password"];
$Role="Null";
// $Role = "User";

$sql="SELECT * FROM user_reg_vehicle_details where Mobile_No='$Mobile_No'";

$result= mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
	
$status="Exist";


}
else{
	
	
	$sql="INSERT INTO user_reg_vehicle_details(Full_Name, Owner_Id, DOB, Email_Id, Mobile_No, Address, Model, Vehicle_Identification_No, DOP, Vehicle_Reg_No, Password,Role) VALUES ('$Full_Name', '$Owner_Id', '$DOB', '$Email_Id', '$Mobile_No', '$Address', '$Model', '$Vehicle_Identification_No', '$DOP', '$Vehicle_Reg_No', '$Password','User');";
	
	if(mysqli_query($con,$sql)){
		
		$status="Success";
	
	}
	
	else{
		$status="Error";
		
	}
}

echo json_encode(array("Response"=>$status));

mysqli_close($con);



?>