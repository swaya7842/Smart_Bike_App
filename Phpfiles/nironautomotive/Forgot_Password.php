<?php

require_once 'connection.php';

$Mobile_No=$_POST["Mobile_No"];
$Password=$_POST["Password"];


$sql="UPDATE user_reg_vehicle_details SET Password = '$Password' WHERE Mobile_No = '$Mobile_No'";
	
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