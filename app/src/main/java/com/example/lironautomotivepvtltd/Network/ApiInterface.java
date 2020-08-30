package com.example.lironautomotivepvtltd.Network;

import com.example.lironautomotivepvtltd.AdminEditUserDetails.EditUserPojo;
import com.example.lironautomotivepvtltd.AdminMyProfile.AdminProfileFetchPojo;
import com.example.lironautomotivepvtltd.AdminMyProfile.AdminProfileUpdatePojo;
import com.example.lironautomotivepvtltd.ForgotPassword.ForgotPassPojo;
import com.example.lironautomotivepvtltd.UserProfile.ProfilePojo;
import com.example.lironautomotivepvtltd.UserProfile.UserDetailPojo;
import com.example.lironautomotivepvtltd.UserSignUp.SignUpPojo;
import com.example.lironautomotivepvtltd.UserVehicleDetails.Vehicle;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Field;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("User_Registration.php")
    Call<SignUpPojo> PerformRegistration
          (@Field("Full_Name") String Full_Name,@Field("Owner_Id") String Owner_Id,
           @Field("DOB") String DOB,@Field("Email_Id") String Email_Id,
           @Field("Mobile_No") String Mobile_No,@Field("Address") String Address,
            @Field("Model") String Model,@Field("Vehicle_Identification_No") String Vehicle_Identification_No,
            @Field("DOP") String DOP,@Field("Vehicle_Reg_No") String Vehicle_Reg_No,
           @Field("Password") String Password);


    @FormUrlEncoded
    @POST("Login.php")
   Call<ResponseBody> PerformLogin(@Field("Mobile_No") String Mobile_No, @Field("Password") String Password);


    @FormUrlEncoded
    @POST("Vehicle_Details_fetch.php")
    Call<List<Vehicle>> getVehicles(@Field("Mobile_No") String Mobile_No);

   @FormUrlEncoded
    @POST("user_fetch.php")
    Call<List<UserDetailPojo>> UserDetails(@Field("Mobile_No") String Mobile_No);

    @FormUrlEncoded
    @POST("User_Updation.php")
    Call<ProfilePojo> PerformUpdatation(@Field("Mobile_No") String Mobile_No,@Field("Full_Name") String Full_Name,
                                        @Field("DOB") String DOB,@Field("Email_Id") String Email_Id,
                                       @Field("Address") String Address,
                                        @Field("Model") String Model,
                                        @Field("DOP") String DOP);
//   Admin
    @FormUrlEncoded
    @POST("Admin_Fetch.php")
    Call<List<AdminProfileFetchPojo>> AdminDetails(@Field("Mobile_No") String Mobile_No);

    @FormUrlEncoded
    @POST("Admin_Update.php")
    Call<AdminProfileUpdatePojo> PerformAdminUpdatation(@Field("Mobile_No") String Mobile_No, @Field("Full_Name") String Full_Name,
                                                        @Field("DOB") String DOB, @Field("Email_Id") String Email_Id,
                                                        @Field("Address") String Address,
                                                        @Field("Designation") String Designation);
    @FormUrlEncoded
    @POST("Admin_Edit_User_Updation.php")
    Call<EditUserPojo> PerformUserUpdatation(@Field("Mobile_No") String Mobile_No, @Field("Owner_Id") String Owner_Id,
                                             @Field("Model") String Model, @Field("DOP") String DOP,
                                             @Field("Vehicle_Reg_No") String Vehicle_Reg_No, @Field("Vehicle_Identification_No") String Vehicle_Identification_No);


    @FormUrlEncoded
    @POST("Forgot_Password.php")
    Call<ForgotPassPojo> PerformForgotPass(@Field("Mobile_No") String Mobile_No,@Field("Password") String Password);

}
