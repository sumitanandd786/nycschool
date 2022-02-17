package com.example.a20220216_sumitanand_nycschools.model

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName

data class ResponseHighSchool(val results: List<HighSchoolResultsItem>? = null)

data class HighSchoolResultsItem(
    @field:SerializedName("dbn")
    val dbn: String? = null,

    @field:SerializedName("school_name")
    val schoolName: String? = null,

    @field:SerializedName("boro")
    val boro: String? = null,

    @field:SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @field:SerializedName("school_10th_seats")
    val tenthSeats: String? = null,
    @field:SerializedName("academicopportunities1")
    val academicOppOne: String? = null,
    @field:SerializedName("academicopportunities2")
    val academicOppTwo: String? = null,

    @field:SerializedName("ell_programs")
    val ellProgram: String? = null,
    @field:SerializedName("neighborhood")
    val neighbourHood: String? = null,
    @field:SerializedName("building_code")
    val buildingCode: String? = null,
    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("phone_number")
    val phoneNumber: String? = null,
    @field:SerializedName("fax_number")
    val faxNumber: String? = null,
    @field:SerializedName("school_email")
    val schoolEmail: String? = null,
    @field:SerializedName("website")
    val websiteUrl: String? = null,


    @field:SerializedName("subway")
    val subway: String? = null,
    @field:SerializedName("bus")
    val bus: String? = null,
    @field:SerializedName("grades2018")
    val grades2018: String? = null,
    @field:SerializedName("finalgrades")
    val finalGrades: String? = null,


    @field:SerializedName("total_students")
    val totalStudents: String? = null,
    @field:SerializedName("extracurricular_activities")
    val extraCurricularActivity: String? = null,
    @field:SerializedName("school_sports")
    val schoolSports: String? = null,
    @field:SerializedName("attendance_rate")
    val attendanceRate: String? = null,

    @field:SerializedName("pct_stu_enough_variety")
    val pctStuEnoughVariety: String? = null,
    @field:SerializedName("pct_stu_safe")
    val pctStuSafe: String? = null,
    @field:SerializedName("school_accessibility_description")
    val schoolAccessibilityDes: String? = null,
    @field:SerializedName("directions1")
    val directionOne: String? = null,

    @field:SerializedName("requirement1_1")
    val reqOne: String? = null,
    @field:SerializedName("requirement2_1")
    val reqTwo: String? = null,
    @field:SerializedName("requirement3_1")
    val reqThree: String? = null,
    @field:SerializedName("requirement4_1")
    val reqFour: String? = null,
    @field:SerializedName("requirement5_1")
    val reqFive: String? = null,

    @field:SerializedName("offer_rate1")
    val offerRateOne: String? = null,
    @field:SerializedName("program1")
    val programOne: String? = null,
    @field:SerializedName("code1")
    val codeOne: String? = null,
    @field:SerializedName("interest1")
    val interestOne: String? = null,

    @field:SerializedName("method1")
    val methodOne: String? = null,
    @field:SerializedName("seats9ge1")
    val seats9egi: String? = null,
    @field:SerializedName("grade9gefilledflag1")
    val grade9gFilledFlagOne: String? = null,
    @field:SerializedName("grade9geapplicants1")
    val grade9geApplicantsOne: String? = null,
    @field:SerializedName("seats9swd1")
    val seats9swd1: String? = null,


    @field:SerializedName("grade9swdfilledflag1")
    val grade9SwdFilledFlagOne: String? = null,
    @field:SerializedName("grade9swdapplicants1")
    val grade9SwdApplicantsOne: String? = null,
    @field:SerializedName("seats101")
    val seats101: String? = null,
    @field:SerializedName("admissionspriority11")
    val admissionsPriority11: String? = null,
    @field:SerializedName("admissionspriority21")
    val admissionsPriority21: String? = null,

    @field:SerializedName("admissionspriority31")
    val admissionsPriority31: String? = null,
    @field:SerializedName("grade9geapplicantsperseat1")
    val grade9geApplicantSperSeat1: String? = null,
    @field:SerializedName("grade9swdapplicantsperseat1")
    val grade9SwdApplicantSperSeat1: String? = null,
    @field:SerializedName("primary_address_line_1")
    val primaryAddressLineOne: String? = null,
    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("zip")
    val zip: String? = null,
    @field:SerializedName("state_code")
    val stateCode: String? = null,
    @field:SerializedName("latitude")
    val latitude: String? = null,
    @field:SerializedName("longitude")
    val longitude: String? = null,
    @field:SerializedName("community_board")
    val communityBoard: String? = null,

    @field:SerializedName("council_district")
    val councilDistrict: String? = null,
    @field:SerializedName("census_tract")
    val censusTract: String? = null,
    @field:SerializedName("bin")
    val bin: String? = null,
    @field:SerializedName("bbl")
    val bbl: String? = null,
    @field:SerializedName("nta")
    val nta: String? = null,
    @field:SerializedName("borough")
    val borough: String? = null
    )

@BindingAdapter("app:website")
fun setWebSite(website : TextView, websiteUrl: String) {
    website.text = websiteUrl
}

@BindingAdapter("app:schoolName")
fun setSchoolName(schoolNm : TextView, schoolName: String) {
    schoolNm.text = schoolName
}


@BindingAdapter("app:schoolLocation")
fun setSchoolLocation(schoolLocation : TextView, schoolLocations: String) {
    schoolLocation.text = schoolLocations
}

@BindingAdapter("app:schoolEmail")
fun setSchoolEmail(schoolEmail : TextView, email: String) {
    schoolEmail.text = email
}

@BindingAdapter("app:schoolPhone")
fun setSchoolPhone(schoolPhone : TextView, phone: String) {
    schoolPhone.text = phone
}

@BindingAdapter("app:schoolFax")
fun setSchoolFax(schoolFax : TextView, fax: String) {
    schoolFax.text = fax
}
