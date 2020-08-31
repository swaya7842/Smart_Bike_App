<?php

require_once 'connection.php';

 
$Mobile_No=$_POST["Mobile_No"];
$Full_Name=$_POST["Full_Name"];
$DOB=$_POST["DOB"];
$Email_Id=$_POST["Email_Id"];
$Address=$_POST["Address"];
$Model=$_POST["Model"];
$DOP=$_POST["DOP"];

$sql="UPDATE user_reg_vehicle_details SET Full_Name = '$Full_Name',DOB =  '$DOB',  Email_Id = '$Email_Id', Address = '$Address', Model = '$Model', DOP = '$DOP' WHERE Mobile_No = '$Mobile_No'";
	
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