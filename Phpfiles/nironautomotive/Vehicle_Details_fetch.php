<?php

require "connection.php";
 $Mobile_No=$_POST["Mobile_No"];

    $query = "SELECT Model, Vehicle_Identification_No, Vehicle_Reg_No FROM user_reg_vehicle_details WHERE Mobile_No='$Mobile_No'";
    // ";
   
	$result = $con ->prepare($query);

    $result -> execute();
    $result->bind_result($Model,$Vehicle_Identification_No,$Vehicle_Reg_No);

	$vehicle_details = array();

	while ($result->fetch()) {
	$temp = array();
	$temp['Model'] = $Model;
	$temp['Vehicle_Identification_No'] = $Vehicle_Identification_No;
	$temp['Vehicle_Reg_No'] = $Vehicle_Reg_No;
	array_push($vehicle_details , $temp);
}

 echo json_encode($vehicle_details);







?>