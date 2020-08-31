<?php


if ($_SERVER['REQUEST_METHOD']=='POST') {


    include_once("connection.php");
	
	$Mobile_No=$_POST["Mobile_No"];
	$Password=$_POST["Password"];	

	if ($Mobile_No == '' || $Password == '') {

        // $status = "False";
		echo json_encode(array("Response" =>  "False" , "Message" => "Parameter missing!" ));
	}
	else{

		$query = "SELECT * FROM user_reg_vehicle_details where Mobile_No='$Mobile_No' and Password='$Password'";
         
         $result = mysqli_query($con, $query);


         if (mysqli_num_rows($result) > 0) {

	        $query = "SELECT * FROM user_reg_vehicle_details where Mobile_No='$Mobile_No' and Password='$Password'";

	        $result = mysqli_query($con, $query);

	        $userarray = array();
	
	          if (mysqli_num_rows($result) > 0) {
	          

	          	while ($row = mysqli_fetch_assoc($result)) {

	          		// $userarray['Mobile_No'] = $Mobile_No;

	          		$userrole = $row['Role'];
	          		if ($userrole == "Admin") {
	          			$role_login = $userrole;
	          		
	          			 // echo json_encode(array("Response" => "True" , "Message" => "Login successfully!","Role" => $userrole));
	          		}
	          		else if ($userrole == "User") {
	          			$role_login = $userrole;
	          			 // echo json_encode(array("Response" => "True" , "Message" => "Login successfully!","Role" => $userrole));
	          		}

	          		// echo $role_login;
	          	}

	          }

               $status = "True";
	           echo json_encode(array("Response" => "True" , "Message" => "Login successfully!","Role" => $userrole));
   
         }
         else{ 
         		// $status = "False";
	        	echo json_encode(array( "Response" => "False","Message" => "Invalid Mobile No or Password!") );
	        }
	         mysqli_close($con);
	}
}

else{
	        // $status = "False";
			echo json_encode(array( "Response" =>  "False","Message" => "Error occured, please try again!") );
	}





?>