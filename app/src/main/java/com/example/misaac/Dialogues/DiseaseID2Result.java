package com.example.misaac.Dialogues;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.example.misaac.test1.R;

public class DiseaseID2Result extends Dialog {

    DiseaseID2Result _current_dialog;

    public DiseaseID2Result(Activity context){
        super(context);
        _current_dialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_disease_id_2_result);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        initialize();
    }

    public void ShowInfo(){

        String Result = "Some Descriptions ....";

        ((TextView)findViewById(R.id.ResultYZR)).setText(Result);
        AddCancelBtn();
    }

    public void initialize(){


        SharedPreferences pref = getContext().getSharedPreferences("private",Context.MODE_PRIVATE);
        int DiseaseID = Integer.parseInt( pref.getString("DiseaseID","-1") );

        int DiseaseID1Info = Integer.parseInt( pref.getString("DiseaseID1Info","-1") );
        if (DiseaseID1Info == 1){
            pref.edit().putString("DiseaseID1Info","-1").commit();
            ShowInfo();
            return;
        }

        if(DiseaseID == 2){

            int DiseaseID2Options = Integer.parseInt(pref.getString("DiseaseID2Options","-1"));


            if (DiseaseID2Options == 1){

                double age = Double.parseDouble(pref.getString("age","-1"));
                double weight = Double.parseDouble(pref.getString("weight","-1"));
                int is_in_months = Integer.parseInt(pref.getString("is_in_months","-1"));

                if (is_in_months == 0 && age / 12.0 < 1 ){
                    /// In months and less than 1 year
                    if (age <= 3){
                        double x = weight * 20.0;
                        double y = x / 50.0;
                        double z = (x / 5.0) - y;
                        double r = ( y + z ) / 60.0;

                        ((TextView)findViewById(R.id.ResultYZR)).setText("Acyclovir \n Dose 10mg/KG \n Equivalent"+ System.getProperty("line.separator")+y + System.getProperty("line.separator") +"Dilution volume"+ System.getProperty("line.separator")+z+ System.getProperty("line.separator") +"Infusion rate"+ System.getProperty("line.separator")+ r );
                    }else if (age > 3 && age <= 12){
                        //// احسب ل10mg
                        double x1 = weight * 10.0;
                        double y1 = x1 / 50.0;
                        double z1 = (x1 / 5.0) - y1;
                        double r1 = ( y1 + z1 ) / 60.0;

                        /// احسب ل15mg
                        double x2 = weight * 15.0;
                        double y2 = x2 / 50.0;
                        double z2 = (x2 / 5.0) - y2;
                        double r2 = ( y2 + z2 ) / 60.0;

                        ((TextView)findViewById(R.id.ResultYZR)).setText("Acyclovir \n Dose 10mg/KG \nEquivalent"+ System.getProperty("line.separator")+"from = "+y1+" to "+ y2 + System.getProperty("line.separator") + "Dilution volume" + System.getProperty("line.separator") + "from = "+z1+ " to "+z2+ System.getProperty("line.separator")+"Infusion rate"+ System.getProperty("line.separator") +"from = "+r1+" to "+r2);
                    }
//                    ((TextView)findViewById(R.id.ResultAge)).setText("In months => "+age);
//                    ((TextView)findViewById(R.id.ResultWeight)).setText("weight => " +  weight);
                }else if ( (is_in_months == 0 && age / 12.0 >= 1 ) || (is_in_months == 1 && age >= 1 ) ){
                    ////In months but more than 1 year OR In years

                    double x = weight * 10.0;
                    double y = x / 50.0;
                    double z = (x / 5.0) - y;
                    double r = ( y + z ) / 60.0;

                    ((TextView)findViewById(R.id.ResultYZR)).setText("Acyclovir \n Dose 10mg/KG \nEquivalent"+ System.getProperty("line.separator")+y + System.getProperty("line.separator") +"Dilution volume"+ System.getProperty("line.separator")+z+ System.getProperty("line.separator") +"Infusion rate"+ System.getProperty("line.separator")+ r );

//                    ((TextView)findViewById(R.id.ResultAge)).setText("In Years => "+age);
//                    ((TextView)findViewById(R.id.ResultWeight)).setText("weight => " +  weight);
                }


            }

        }else if (DiseaseID == 1){

            int DiseaseOption = Integer.parseInt( pref.getString("DiseaseOptions","-1") );

            String Result = null;

            switch (DiseaseOption) {

                /// Adults
                case 1:

                Double weight = Double.parseDouble(pref.getString("weight", "-1"));
                //// Vancomycin (15mg)
                double xVan15 = 15 * weight;
                double yVan15 = xVan15 * 10.0 / 500.0;
                double zVan15 = xVan15 / 5.0;
                double R_Van15 = (yVan15 + zVan15) / 60.0;

                //// Vancomycin (20mg)
                double xVan20 = 15 * weight;
                double yVan20 = xVan20 * 10.0 / 500.0;
                double zVan20 = xVan20 / 5.0;
                double R_Van20 = (yVan20 + zVan20) / 60.0;

                /// Pencillin G
                double xPenc = 66667 * weight;
                double yPenc = xPenc * 10 / 1000000;


                /// Dexamethazone
                double xDexa = 0.15 * weight;
                double yDexa = xDexa * 2.0 / 8.0;

                 Result = "Vancomycin(15mg) \n"
                         + "Recopnstitute by 10 ml of NaCl \n"
                        + "Dose needed \n"
                        + xVan15 + " mg q 8 - 12 hrs \n"
                        + "Equivalent to  \n"
                        + yVan15 + " ml  \n"
                        + "Dilution volume \n"
                        + zVan15 + " ml.\n"
                        + "Infusion rate\n"
                        + R_Van15 + " ml/min\n\n\n"


                        + "Vancomycin(20mg) \n"
                         + "Recopnstitute by 10 ml of NaCl \n"
                        + "Dose needed \n"
                        + xVan20 + " mg q 8 - 12 hrs \n"
                         + "Equivalent to  \n"
                         + yVan20 + " ml  \n"
                        + "Dilution volume \n"
                        + zVan20 + " ml.\n"
                        + "Infusion rate\n"
                        + R_Van20 + " ml/min\n\n\n"


                        + "Pencillin G \n"
                        + "Dose needed \n"
                        + xPenc + " (u) q 4 hrs \n"
                        + "Equivelant to \n"
                        + yVan20 + " ml IV bolus over 5 mins \n"
                        + "Or can be further diluted with Nacl 0.9% \n"
                        + "Or dextrose 5% and administer over 30 mins IV infusion \n\n\n"


                        + "Ceftriaxone \n"
                        + "Reconstitute with 10 ml SWL \n"
                        + "Dose Required 2000 mg (2g) q 12 hrs \n"
                        + "Equivalent to 20 ml(cm) 1V bolus over 5 mins \n\n\n"


                        + "Cefotaxime \n"
                        + "Reconstitute with 5ml 5wl \n"
                        + "Dose Required 2000-3000 q 6 hrs \n"
                        + "Equivalent to 10-15 ml(cm) 1vbolus over 5mins \n\n\n"


                        + "cefepime (1gm vial) \n"
                        + "Reconstitute with 10 ml swl \n"
                        + "Dose Required 2000 mg (2g) q 8 hrs \n"
                        + "Equivalent to 20ml(cm)1v bolus every 5 mins \n"
                        + "Or can be further diluted with NaCl 0.9% or dextrose 5% administer over 30 mins 1v infusion \n\n\n"


                        + "Ceftazidine \n"
                        + "Reconstitute with 10 ml swl \n"
                        + "Dose required 2000 mg q 8 hrs \n"
                        + "Equivalent to 20 ml(cm) 1V bolus over 5 mins \n"
                        + "Or can be further diluted with NaCl 0.9% or dextrose 5% administer over 30 mins 1v infusion \n\n\n"


                        + "Meropenam (IV infusion)\n"
                        + "Reconstitute with 20 ml swl\n"
                        + "Dose required 2000 mg q 8 hrs\n"
                        + "Equivalent to 40 ml (cm)\n"
                        + "Dilution : 100 - 2000 ml(cm) NaCl 0.9% \n"
                        + "Infusion Rate 4.67 - 68 ml/min over 30 mins \n\n\n"


                        + "Meropenam (IV bolus)\n"
                        + "Reconstitute with 20 ml swl\n"
                        + "Dose Required 2000 mg q 8 hrs \n"
                        + "Equivalent to 40 ml (cm) 1V bolus over 5 mins \n\n\n"


                        + "Refampian \n"
                        + "Dose 600 mg/day \n"
                        + "Equivalent to 2 capsules / day \n\n\n"


                        + "Dexamethazone (8mg/2ml ampoule)\n"
                        + "Dose " + xDexa + " mg q 6 hrs (1V bolus)\n"
                        + "Equivalent to " + yDexa + " ml(cm) over 2-3 mins for 2-4 days only \n\n\n";
                 break;
                 //// Pediatrics
                case 2:
                    Double weight_P = Double.parseDouble(pref.getString("weight", "-1"));
                    //// Vancomycin (15mg)
                    double xVan15_P = 15 * weight_P;
                    double yVan15_P = xVan15_P * 10.0 / 500.0;
                    double zVan15_P = xVan15_P / 5.0;
                    double R_Van15_P = (yVan15_P + zVan15_P) / 60.0;


                    /// Pencillin G
                    double xPenc_P = 66667 * weight_P;
                    double yPenc_P = xPenc_P * 10 / 1000000;


                    //// ceftriaxone
                    double x1Ceftria_P = 80 * weight_P;
                    double x2Ceftria_P = 100 * weight_P;
                    double y1Ceftria_P = x1Ceftria_P * 10.0 / 1000.0;
                    double y2Ceftria_P = x2Ceftria_P * 10.0 / 1000.0;
                    double z11Ceftria_P = x1Ceftria_P / 10.0;
                    double z12Ceftria_P = x1Ceftria_P / 40.0;
                    double z21Ceftria_P = x2Ceftria_P / 10.0;
                    double z22Ceftria_P = x2Ceftria_P / 40.0;
                    double R1Ceftria_P = (y1Ceftria_P+z11Ceftria_P)/30.0;
                    double R2Ceftria_P = (y1Ceftria_P+z12Ceftria_P)/30.0;
                    double R3Ceftria_P = (y2Ceftria_P+z21Ceftria_P)/30.0;
                    double R4Ceftria_P = (y2Ceftria_P+z22Ceftria_P)/30.0;


                    //// Cefotaxime
                    double x1Cefotaxime_P = 75.0 * weight_P;
                    double x2Cefotaxime_P = 100.0 * weight_P;
                    double y1Cefotaxime_P = x1Cefotaxime_P * 5 / 1000.0;
                    double y2Cefotaxime_P = x2Cefotaxime_P * 5 / 1000.0;


                    //// cefepime
                    double xCefepime_P = weight_P * 50;
                    double yCefepime_P = xCefepime_P * 10.0/1000.0;


                    //// Ceflazidine
                    double xCeflazidine_P = weight_P * 50;
                    double yCeflazidine_P = xCeflazidine_P * 10.0 / 1000.0;


                    //// MeropenamInfusion
                    double x1MeropenamInfusion_P = weight_P * 20.0;
                    double x2MeropenamInfusion_P = weight_P * 40.0;
                    double y1MeropenamInfusion_P = x1MeropenamInfusion_P * 20.0 / 1000.0;
                    double y2MeropenamInfusion_P = x2MeropenamInfusion_P * 20.0 / 1000.0;
                    double z11MeropenamInfusion_P = x1MeropenamInfusion_P ;
                    double z12MeropenamInfusion_P = x1MeropenamInfusion_P / 20;
                    double z21MeropenamInfusion_P = x2MeropenamInfusion_P ;
                    double z22MeropenamInfusion_P = x2MeropenamInfusion_P / 20;
                    double R1MeropenamInfusion_P = (y1MeropenamInfusion_P + z11MeropenamInfusion_P)/30.0;
                    double R2MeropenamInfusion_P = (y1MeropenamInfusion_P + z12MeropenamInfusion_P)/30.0;
                    double R3MeropenamInfusion_P = (y2MeropenamInfusion_P + z21MeropenamInfusion_P)/30.0;
                    double R4MeropenamInfusion_P = (y2MeropenamInfusion_P + z22MeropenamInfusion_P)/30.0;


                    //// MeropenamBolus
                    double x1MeropenamBolus_P = 20.0*weight_P;
                    double x2MeropenamBolus_P = 40.0*weight_P;
                    double y1MeropenamBolus_P = x1MeropenamBolus_P*20.0/1000.0;
                    double y2MeropenamBolus_P = x2MeropenamBolus_P*20.0/1000.0;


                    //// Refampian
                    double x1Refampian_P = weight_P * 5.0;
                    double x2Refampian_P = weight_P * 10.0;
                    double x3Refampian_P = weight_P * 10.0;
                    double x4Refampian_P = weight_P * 20.0;
                    double y1Refampian_P = x1Refampian_P * 5.0 / 100.0;
                    double y2Refampian_P = x2Refampian_P * 5.0 / 100.0;
                    double y3Refampian_P = x3Refampian_P * 5.0 / 100.0;
                    double y4Refampian_P = x4Refampian_P * 5.0 / 100.0;


                    /// Dexamethazone
                    double xDexa_P = 0.15 * weight_P;
                    double yDexa_P = xDexa_P * 2.0 / 8.0;

                    Result = "Vancomycin(15mg) \n"
                            + "Reconstitute with 10 ml NaCl or descrose 5% or SWL \n"
                            + "Dose needed \n"
                            + xVan15_P + " mg q 6 hrs \n"
                            + "Equivalent to \n"
                            + yVan15_P + " ml \n"
                            + "Dilution volume \n"
                            + zVan15_P + " ml NaCl 0.9 % \n"
                            + "Infusion rate \n"
                            + R_Van15_P + " ml/min over 60 min \n\n\n"


                            + "Pencillin G \n"
                            + "Reconstitute with 10 ml NaCl or SWL (not ringer)"
                            + "Dose needed \n"
                            +  xPenc_P + " (u) q 4 hrs (400,000 v/kg/day) \n"
                            + "Equivelant to \n"
                            +  yPenc_P + " ml IV bolus over 5 mins \n"
                            + "Or can be further diluted with Nacl 0.9% \n"
                            + "Or dextrose 5% and administer over 30 mins IV infusion \n\n\n"


                            + "Ceftriaxone \n"
                            + "Dose Required \n"
                            + "Range from "+x1Ceftria_P + " to "+ x2Ceftria_P + " inq q 12 hrs twice daily \n"
                            + "Equivalent to : from "+ y1Ceftria_P +" to " +y2Ceftria_P+ "\n"
                            + "Dose 80 mg/kg q 12 hrs \n"
                            + "Dilution from "+ z11Ceftria_P +" to "+z12Ceftria_P +" ml NaCl 0.9% \n"
                            + "Dose 100 mg/kg q 12 hrs \n"
                            + "Dilution from "+ z21Ceftria_P + " to "+z22Ceftria_P+" ml NaCl 0.9% \n"
                            + "Infusion rate of dose 80 mg/kg q 12 hrs \n"
                            + "from "+R1Ceftria_P+" to "+R2Ceftria_P +" ml/min over 30 min \n"
                            + "Infusion rate of dose 100 mg/kg q 12 hrs \n"
                            + "from "+R3Ceftria_P+" to "+R4Ceftria_P +" ml/min over 30 min \n\n\n"


                            + "Cefotaxime \n"
                            + "Reconstitute with 5ml 5wl \n"
                            + "Dose Required from "+ x1Cefotaxime_P + " to " + x2Cefotaxime_P +" q 8 hrs \n"
                            + "Equivalent from "+y1Cefotaxime_P +" to " + y2Cefotaxime_P +" ml 1vbolus over 5mins \n\n\n"


                            + "cefepime (1gm vial) \n"
                            + "Reconstitute with 10 ml swl \n"
                            + "Dose Required "+ xCefepime_P +" mg q 8 hrs \n"
                            + "Equivalent to "+yCefepime_P+" IV bolus over 5 mins \n"
                            + "Or can be further diluted with NaCl 0.9% or dextrose 5% administer over 30 mins 1v infusion \n\n\n"


                            + "Ceftazidine \n"
                            + "Reconstitute with 10 ml swl \n"
                            + "Dose required "+xCeflazidine_P+" mg q 8 hrs \n"
                            + "Equivalent to "+yCeflazidine_P+" ml(cm) 1V bolus over 5 mins \n"
                            + "Or can be further diluted with NaCl 0.9% or dextrose 5% administer over 30 mins 1v infusion \n\n\n"


                            + "Meropenam (IV infusion)(20-40mg/kg)\n"
                            + "Reconstitute with 20 ml swl\n"
                            + "Dose required from "+x1MeropenamInfusion_P +" to "+x2MeropenamInfusion_P+" mg q 8 hrs\n"
                            + "Equivalent to : from "+y1MeropenamInfusion_P+ " to "+y2MeropenamInfusion_P+" ml (cm)\n"
                            + "Dilution if Dose 20 mg/kg NaCl 0.9% \n"
                            + "from "+z11MeropenamInfusion_P+" to "+z12MeropenamInfusion_P+"\n"
                            + "Dilution if Dose 40 mg/kg NaCl 0.9% \n"
                            + "from "+z21MeropenamInfusion_P+" to "+z22MeropenamInfusion_P+"\n"
                            + "Infusion Rate if Dose 20 mg/kg \n"
                            + "from "+R1MeropenamInfusion_P+" to "+R2MeropenamInfusion_P+"ml/min over 30 min\n"
                            + "Infusion Rate if Dose 40 mg/kg \n"
                            + "from "+R3MeropenamInfusion_P+" to "+R4MeropenamInfusion_P+"ml/min over 30 min\n\n\n"


                            + "Meropenam (IV bolus)\n"
                            + "Reconstitute with 20 ml swl\n"
                            + "Dose Required from "+x1MeropenamBolus_P+" to "+x2MeropenamBolus_P+" mg q 8 hrs \n"
                            + "Equivalent to : from "+y1MeropenamBolus_P+" to "+y2MeropenamBolus_P+" ml 1V bolus over 5 mins \n\n\n"


                            + "Refampian Q 12 hrs\n"
                            + "Dose from "+x1Refampian_P+" to "+x2Refampian_P+" mg q 12 hrs \n"
                            + "Equivalent to : from "+y1Refampian_P+" to "+y2Refampian_P+" ml.syrup \n"
                            + "Refampian Q 24 hrs\n"
                            + "Dose from "+x3Refampian_P+" to "+x4Refampian_P+" mg q 12 hrs \n"
                            + "Equivalent to : from "+y3Refampian_P+" to "+y4Refampian_P+" ml.syrup \n\n\n"


                            + "Dexamethazone (8mg/2ml ampoule)\n"
                            + "Dose " + xDexa_P + " mg q 6 hrs (1V bolus)\n"
                            + "Equivalent to " + yDexa_P + " ml(cm) over 2-3 mins for 2-4 days only \n\n\n";
                    break;

            }



            ((TextView)findViewById(R.id.ResultYZR)).setText(Result);

        }else if (DiseaseID == 3){
            /// TB meningitis

            int DiseaseOption = Integer.parseInt( pref.getString("DiseaseOptions","-1") );

            String Result = null;
            Double weight ;
            switch (DiseaseOption){
                //// adults
                case 1:
                    weight = Double.parseDouble(pref.getString("weight", "-1"));

                    double dexa_X1 = 0.3 * weight;
                    double dexa_X2 = 0.4 * weight;
                    double dexa_X3 = 0.2 * weight;

                    double Ethambatol_X_1 = 15 * weight;
                    double Ethambatol_X_2 = 25 * weight;
                    double Ethambatol_Y_1 = Ethambatol_X_1 / 500.0;
                    double Ethambatol_Y_2 = Ethambatol_X_2 / 500.0;


                    Result = "Isoniazide(oral)\n"
                            +"3 tabltes / day\n\n\n"

                            +"Rifampicin(oral)\n"
                            +"2-4 capsuls/day (according to concentration) \n\n\n"

                            +"Pyrazinamide\n"
                            +"4 tablets\n\n\n"

                            +"Dexamethasone\n"
                            + "Dose : "+ dexa_X1+" - " + dexa_X2 + " for week 1 & 2 \n"
                            + "Dose : "+ dexa_X3+" for week 3 & 4 \n"
                            + "4 mg/day and tapper(decrease) gradually 1mg off the daily dose each week(5,6,7,8)\n\n\n"

                            +"prednisolone\n"
                            +"12 tablet/day , administer 1st dose for 2 weeks then tapper gradually over next 6 weeks\n"
                            +"(reduce daily dose by 10 mg each week)\n\n\n"

                            +"Ethambutol(oral)\n"
                            +"Dose 15 - 25 mg/kg/day\n"
                            +"From " + Ethambatol_Y_1 + " to " +Ethambatol_Y_2+ " tablets/day\n\n\n"


                            +"Prednisolone \n"
                            + "Dose 60 mg/day \n"
                            + "12 tablets/day , administer 1st dose for 2 weeks then tapper gradually over next 6 weeks (reduce daily dose by 10 mg each week)\n\n\n";

                    break;
                /// pediatrics
                case 2:
                    weight = Double.parseDouble(pref.getString("weight", "-1"));

                    double dexa2_X1 = 0.3 * weight;
                    double dexa2_X2 = 0.4 * weight;
                    double dexa2_X3 = 0.2 * weight;

                    double X1Isoniaid = weight * 10;
                    double X2Isoniaid = weight * 15;
                    double Y1Isonied =  X1Isoniaid / 100.0;
                    double Y2Isonied =  X2Isoniaid / 100.0;

                    Double XRifampicin = 10 * weight;
                    double Y1Rifampicin = XRifampicin.intValue() / 150.0;
                    double Y2Rifampicin = XRifampicin.intValue() / 300.0;
                    double Y3Rifampicin = XRifampicin * 5.0 / 100.0;

                    Double X1Pyrazinamide = weight * 15.0;
                    Double X2Pyrazinamide = weight * 30.0;
                    double Y1Pyrazinamide = X1Pyrazinamide.intValue() / 500.0;
                    double Y2Pyrazinamide = X2Pyrazinamide.intValue() / 500.0;

                    double Ethambatol_X_1_A = 15 * weight;
                    double Ethambatol_X_2_A = 25 * weight;
                    double Ethambatol_Y_1_A = Ethambatol_X_1_A / 500.0;
                    double Ethambatol_Y_2_A = Ethambatol_X_2_A / 500.0;

                    Double X1Prednisolone = weight * 2;
                    Double X2Prednisolone = weight * 4;
                    double Y1Prednisolone = X1Prednisolone.intValue() / 5.0;
                    double Y2Prednisolone = X2Prednisolone.intValue() / 5.0;

                    Result = "Isoniazide (Oral) \n"
                            +"from "+X1Isoniaid+" to "+X2Isoniaid+" mg \n"
                            +"from "+Y1Isonied+" to "+Y2Isonied+" tablets/day \n\n\n"

                            +"Dexamethasone\n"
                            + "Dose : "+ dexa2_X1+" - " + dexa2_X2 + " for week 1 & 2 \n"
                            + "Dose : "+ dexa2_X3+" for week 3 & 4 \n"
                            + "4 mg/day and tapper(decrease) gradually 1mg off the daily dose each week(5,6,7,8)\n\n\n"

                            +"Rifampicin (Oral) \n"
                            +XRifampicin+" mg"
                            +"from "+Y1Rifampicin+" to "+Y2Rifampicin+" capsules/day"
                            +Y3Rifampicin+" (ml)(cm)syrup \n\n\n"

                            +"Pyrazinamide (Oral)\n"
                            +"from "+X1Pyrazinamide+" to "+X2Pyrazinamide+" mg \n"
                            +"from "+Y1Pyrazinamide+" to "+Y2Pyrazinamide+" tablets/day \n\n\n"

                            +"Ethambutol (Oral)\n"
                            +"Dose 15 - 25 mg/kg/day\n"
                            +"From " + Ethambatol_Y_1_A + " to " +Ethambatol_Y_2_A + " tablets/day\n\n\n"

                            +"Dexamethazone \n"
                            +"8 mg /day for 2 weeks and then tapper gradually over 4 to 6 weeks \n\n\n"

                            +"Prednisolone\n"
                            +"Dose from "+X1Prednisolone+" to "+X2Prednisolone+" mg \n"
                            +"from "+Y1Prednisolone+" to "+Y2Prednisolone+" tablets/day \n\n\n";
                    break;
            }

            ((TextView)findViewById(R.id.ResultYZR)).setText(Result);

        }else if (DiseaseID == 4){
            int DiseaseID4Option = Integer.parseInt( pref.getString("DiseaseID4Option","-1") );

            double age = Double.parseDouble(pref.getString("age","-1"));
            double weight = Double.parseDouble(pref.getString("weight","-1"));
            int is_in_months = Integer.parseInt(pref.getString("is_in_months","-1"));

            String Result = "";
            switch (DiseaseID4Option){
                case 1:
                    /////anticonvulsants

                    String sub_result_acute_treatment = "";
                    if ( (is_in_months == 0 && age <= 5) || (is_in_months==1 && (age/12.0) <= 5.0 ) ){
                        double X1AcuteTreat = 0.2 * weight;
                        double X2AcuteTreat = 0.5 * weight;
                        double Y1AcuteTreat = X1AcuteTreat / 5.0;
                        double Y2AcuteTreat = X2AcuteTreat / 5.0;

                        sub_result_acute_treatment = "Dose 0.2 - 0.5 mg/kg \n"
                                +"Dose from "+X1AcuteTreat+" mg to"+X2AcuteTreat+" mg \n"
                                +"Dilution from "+Y1AcuteTreat+" ml to "+Y2AcuteTreat+" ml \n"
                                +"Repeat every 2-5 minutes; Don't exceed 5mg; May repeat 2-4 hours later PRN \n";
                    }else if ( (is_in_months == 0 && age > 5) || (is_in_months == 1 && (age/12.0) > 5.0 ) ){
                        double X1AcuteTreat = 5.0 * weight;
                        double X2AcuteTreat = 10.0 * weight;
                        double Y1AcuteTreat = X1AcuteTreat / 5.0;
                        double Y2AcuteTreat = X2AcuteTreat / 5.0;

                        sub_result_acute_treatment = "Dose 5 - 10 mg/kg \n"
                                +"Dose from "+X1AcuteTreat+" mg to"+X2AcuteTreat+" mg \n"
                                +"Dilution from "+Y1AcuteTreat+" ml to "+Y2AcuteTreat+" ml \n"
                                +"Given Slowly every 2-5 mins ; not to exceed 10 mg total dose ; may repeat in 2-4 hrs if necessary \n"
                                +"Continous infusion is not recommended because of pericipation in I.V fluids and absorption of drug into infusion bags and tubing \n"
                                +"In children donot exceed 1.2 mg/min IVP. Adults 5 mg/min \n";
                    }

                    double X1Phen = 15.0 * weight;
                    double X2Phen = 20.0 * weight;
                    double Y1Phen = X1Phen / 50.0;
                    double Y2Phen = X2Phen / 50.0;
                    double Z1Phen = (X1Phen / 5.0)-Y1Phen;
                    double Z2Phen = (X2Phen / 5.0)-Y2Phen;


                    double X1MainDose = 5.0 * weight;
                    double X2MainDose = 8.0 * weight;
                    double Y1MainDose = X1MainDose / 50.0;
                    double Y2MainDose = X2MainDose / 50.0;
                    double Z1MainDose = (X1MainDose / 5.0)-Y1MainDose;
                    double Z2MainDose = (X2MainDose / 5.0)-Y2MainDose;


                    double X1Dormi = 0.02 * weight;
                    double X2Dormi = 0.04 * weight;
                    double Y1Dormi = X1Dormi / 5.0;
                    double Y2Dormi = X2Dormi / 5.0;
                    double Z1Dormi = X1Dormi - Y1Dormi;
                    double Z2Dormi = X2Dormi - Y2Dormi;


                    double X1Inf = 0.05 * weight;
                    double X2Inf = 0.1 * weight;
                    double Y1Inf = X1Inf / 5.0;
                    double Y2Inf = X2Inf / 5.0;
                    double Z1Inf = X1Inf - Y1Inf;
                    double Z2Inf = ( X1Inf / 0.5 ) - Y1Inf;
                    double Z3Inf = X2Inf - Y2Inf;
                    double Z4Inf = ( X2Inf / 0.5 ) - Y2Inf;


                    double X1Chil = 0.025 * weight;
                    double X3Chil = 0.05 * weight;
                    double Y1Chil = X1Chil / 5.0;
                    double Y3Chil = X3Chil / 5.0;
                    double Z1Chil = X1Chil - Y1Chil;
                    double Z2Chil = ( X1Chil / 0.5 )- Y1Chil;
                    double Z3Chil = X3Chil - Y3Chil;
                    double Z4Chil = ( X3Chil / 0.5 ) - Y3Chil;


                    Result = "Neuril or Valium (10mg/2ml) \n\n"
                            +"Anticonvulsants (Acute treatment) \n"
                            +sub_result_acute_treatment + "\n\n"
                            +"Phynytoin (epanutin) 250/5 \n"
                            +"Pediatrics until 18 years , infants and children : loading dose 15-20 mg/kg \n"
                            +"Dose from "+X1Phen+" to "+X2Phen+" mg/kg \n"
                            +"Dilution from "+Y1Phen+" to "+Y2Phen+" ml \n"
                            +"Reconstitution from "+Z1Phen+" to "+Z2Phen+" ml \n\n\n"
                            +"Maintenance dose 5-8 mg/kg \n"
                            +"Dose from "+X1MainDose+" to "+X2MainDose+ " mg \n"
                            +"Dilution from "+Y1MainDose+" to "+Y2MainDose+" ml \n"
                            +"Reconstitution from "+Z1MainDose+" to "+Z2MainDose+" ml \n\n\n"
                            +"Dormicum (midaolam)(5mg/ml)\n"
                            +"Adults\n"
                            +"Dose from 0.02 to 0.04 mg/kg \n"
                            +"Dose required from "+X1Dormi+" to "+X2Dormi+" mg \n"
                            +"Reconstitution volume from "+Y1Dormi+" to "+Y2Dormi+" ml\n"
                            +"Dilution volume from "+Z1Dormi+" to "+Z2Dormi+" ml \n"
                            +"I.V: Administer by slow IV injection over at least 2-5 mins at a concentration of 1-5 mg/ml or by I.V infusion \n"
                            +"Usual infusion Concentrations :\n"
                            +"For adults IV infusion : 100mg in 100ml (Concentration 1mg/ml) of D5W or NS \n"
                            +"Stable in D5NS , D5W , NS : Incompatible with LR \n"
                            +"Repeat every 5 mins as needed to desired effect or up to 0.1 - 0.2 mg/kg \n"
                            +"Infants less than 6 months : Limited informations is available in non-intubated infants \n"
                            +"Dosing recommendations not clear ,Infants less than 6 months are at higher risk for airway obstrucion and hypoventialation , titrate dose in small increments to desired effect \n"
                            +"Infants 6 months to children 5 years \n"
                            +"Dose from 0.05 - 0.1 mg / kg \n"
                            +"Dose required from "+X1Inf+" to "+X2Inf+" mg \n"
                            +"Reconstitution volume from "+Y1Inf+" to "+Y2Inf+" ml \n"
                            +"Dilution volume \n"
                            +"a) Dilution to reach 1 mg/ml from "+Z1Inf+" to "+Z3Inf+" ml \n"
                            +"b) Dilution to reach 0.5 mg/ml from "+Z2Inf+" to "+Z4Inf+" ml \n"
                            +"Maximum total dose = 6 mg \n"
                            +"Children 6-12 years \n"
                            +"Dose from 0.025 - 0.05 mg/kg \n"
                            +"Dose required from "+X1Chil+" to "+X3Chil+" mg \n"
                            +"Reconstitution volume from "+Y1Chil+" to "+Y3Chil+" ml \n"
                            +"Dilution Volume \n"
                            +"a) Dilution to reach 1mg/ml from "+Z1Chil+" to "+Z3Chil+" ml \n"
                            +"b) Dilution to reach 0.5 mg/ml from "+Z2Chil+" to "+Z4Chil+" ml \n"
                            +"Maximum dose 10 mg \n"
                            +"AAdminister by slow IV injection over at least 2-5 mins at a concentration of 1- mg/ml or by IV infusion \n"
                            +"Usual infusion concentration : Pediantric \n"
                            +"IV infusion 0. mg/ml or 1 mg/ml \n"
                            +"Compatibility : Stable in D5N5 , D5W , N5 but isn't compatible with LR \n\n\n";

                    break;
                case 2:
                    //// Antimetic

                    double XOndan = 0.15 * weight;
                    double YOndan = XOndan / 2.0;

                    double XAntiPed = 0.15 * weight;
                    double YAntiPed = XAntiPed / 2.0;

                    Result = "Ondansitron (dansit) 4mg/2ml\n"
                            +"Adults : \n"
                            +"Minimum 8 mg equivalent to 4ml \n"
                            +"Maximum 16 mg euivalent to 8 ml \n"
                            +"Dose 0.1 mg/kg \n"
                            +"Dose required "+XOndan+" mg \n"
                            +"Diluation "+YOndan+" ml \n"
                            +"Dilute over 50 ml NS or D5W over 1 mins \n"
                            +"(Maximum : 16 mg / dose ) over 15 mins \n"
                            +"Pediatrics IV : Children 6 months to 18 years \n"
                            +"Dose 0.15 mg/kg \n"
                            +"Dose required "+XAntiPed+" mg \n"
                            +"Dilution "+YAntiPed+" ml \n"
                            +"Maximum 16 mg/dose dilute over 50ml NS or D5W for 15 mins \n\n\n"
                            +"Cycline Lactate ampoule(50 mg/ml)\n"
                            +"for prevention of postoperative nausea and vomiting administer the first dose by slow IV injection 20 mins before the anticipated end of sergery \n"
                            +"Adults\n"
                            +"50 mg 1M or IV up to 3 times daily \n"
                            +"When used IV should be injected slowly into the blood stream with only minimal with drawal of blood into the synnge \n"
                            +"For prevention of post operative nausea and vomiting administer the first dose by slow 1V injection 20 mins before the anticipated end of surgery \n"
                            +"Pediatric \n"
                            +"Not pincenced for use in children , Method of administration IV or IM\n\n\n"
                            +"Domperidone \n"
                            +"10 mg tablets \n"
                            +"Adults and Adolescents(weighing 35 kg or more)\n"
                            +"The usual dose for treatment 1 - 2 tablets taken 3 - 4 times a day before meals (donot take more than 8 tablets (80 mg/day))\n"
                            +"infants and children < 35 kg shouldnot take this tablet\n"
                            +"the usaual dose for treatment is 0.25 - 0.5 mg/kg taken 3-4 times/day before meals \n\n\n";

                    break;

                case 3:

                    double X1MinPed = weight * 5.0;
                    double X2MaxPed = weight * 10.0;

                    Result = "Antipyretics \n"
                            +"Paramol (120mg/5ml) \n"
                            +"Pediatric \n"
                            +"weight : 2.7-5.3 kg and Age : 0-3 Mo then Dose 40 mg in 1.6 ml \n"
                            +"weight : 5.4-8.1 kg and Age : 4-11 Mo then Dose 80 mg in 3.3 ml \n"
                            +"weight : 8.2-10.8 kg and Age : 1-2 Y then Dose 120 mg in 5 ml \n"
                            +"weight : 10.9-16.3 kg and Age : 2-3 Y then Dose 160 mg in 6.5 ml \n"
                            +"weight : 16.4-21.7 kg and Age : 4-5 Y then Dose 240 mg in 10 ml \n"
                            +"weight : 21.8-27.2 kg and Age : 6-8 Y then Dose 320 mg in 12.8 ml \n"
                            +"weight : 27.3-32.6 kg and Age : 9-10 Y then Dose 400 mg in 16.6 or 1 cap ml \n"
                            +"weight : 32.7-43.2 kg and Age : 11 Y then Dose 480 mg in 20 ml or 1 cap \n"
                            +"Adults \n"
                            +"Oral regular release : 325 - 650 mg every 4-6 Hrs or 1000 mg 3-4 times daily (max : 4gm daily) \n\n\n"
                            +"Ibiprofin \n"
                            +"Adults \n"
                            +"Oral 1 tablet : 200 - 400 mg/dose every 4-6 Hrs max : 1.3 gm \n"
                            +"Pediatrics \n"
                            +"Dose 5-10 mg/KG \n"
                            +"Dose required "+X1MinPed+" to "+X2MaxPed+" mg \n"
                            +"Max daily dose 400 mg \n\n\n"
                            +"Salicylic Acid \n"
                            +"Adults 1 or 2 tablets of 325 mg by maximum 4 g/day \n\n\n";

                    break;
            }

            ((TextView)findViewById(R.id.ResultYZR)).setText(Result);

        }else if (DiseaseID == 7){

            String Result = "Hepafort \n"
                    +"1-2 capsules 3 times after meals \n"
                    +"Farcovit \n"
                    +"1-2 capsules 3 times after meals \n"
                    +"Silymarin \n"
                    +"1 sachet be dissolved in half glass of water 3 times daily after meals (420 mg per day in divided doses considered safe for 41 m)\n"
                    +"Or 1 tablet 3 times daily \n";

            ((TextView)findViewById(R.id.ResultYZR)).setText(Result);
        }else if (DiseaseID == 5){
            String Result = "Diuretics single morning dose 100mg spiranolactone and 40 mg furosemide dose can be titrated up to a maximum of 400 mg spiranolactone abd 160 furosemide at ratio 100:40";
            ((TextView)findViewById(R.id.ResultYZR)).setText(Result);
        }

//        pref.edit().putString("DiseaseID","2");
        AddCancelBtn();

    }

    public void AddCancelBtn(){
        AppCompatButton btn = (AppCompatButton)findViewById(R.id.btn_dialog_close);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _current_dialog.cancel();
            }
        });
    }
}
