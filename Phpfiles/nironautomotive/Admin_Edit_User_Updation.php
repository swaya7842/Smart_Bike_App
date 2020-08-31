<?php

require_once 'connection.php';

 
$Mobile_No=$_POST["Mobile_No"];
$Owner_Id=$_POST["Owner_Id"];
$Model=$_POST["Model"];
$Vehicle_Identification_No=$_POST["Vehicle_Identification_No"];
$DOP=$_POST["DOP"];
$Vehicle_Reg_No=$_POST["Vehicle_Reg_No"];

$sql="UPDATE user_reg_vehicle_details SET Owner_Id = '$Owner_Id',Model =  '$Model',  Vehicle_Identification_No = '$Vehicle_Identification_No', DOP = '$DOP', Vehicle_Reg_No = '$Vehicle_Reg_No' WHERE Mobile_No = '$Mobile_No'";
	
$result= mysqli_query($con,$sql);

	
	
	if(mysqli_query($con,$sql)){
		
		$status="Success";
	
	}
	
	else{
		$status="Error";
		
	}

// }
echo json_encode(array("Response"=>$status));

mysqli_close($con);



?>