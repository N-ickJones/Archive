"""
Purpose: Creates The Database Scheme
Author:  Nicholas Jones
Date:    06/10/2019
Version: 1.0
"""


from django.db import models


# Database Modeling
class Inquiry(models.Model):
    name = models.CharField(max_length=100)
    phone = models.BigIntegerField()
    email = models.EmailField()
    questions = models.TextField()
    date = models.DateTimeField(default=None)


class ReferralForm(models.Model):
    username = models.CharField(max_length=150, primary_key=True)
    patient_first_name = models.CharField(max_length=30)
    patient_last_name = models.CharField(max_length=150)
    social_security_number = models.CharField(max_length=11)
    date_of_birth = models.DateField(default=None)
    gender = models.CharField(max_length=30)
    address = models.CharField(max_length=100)
    city = models.CharField(max_length=30)
    state = models.CharField(max_length=30)
    zip = models.CharField(max_length=7)
    alt_contact_first_name = models.CharField(max_length=30)
    alt_contact_last_name = models.CharField(max_length=30)
    alt_contact_number = models.CharField(max_length=12)
    flu_vaccine = models.DateField(default=None)
    referral_date = models.DateTimeField(default=None)
    primary_care_physician = models.CharField(max_length=181)
    insurance_information = models.CharField(max_length=256)
    diagnosis = models.CharField(max_length=256)


class HospiceForm(models.Model):
    username = models.CharField(max_length=150, primary_key=True)
    patient_first_name = models.CharField(max_length=30)
    patient_last_name = models.CharField(max_length=150)
    gender = models.CharField(max_length=6)
    date_of_birth = models.DateField(default=None)
    address = models.CharField(max_length=100)
    city = models.CharField(max_length=30)
    state = models.CharField(max_length=30)
    zip = models.CharField(max_length=7)
    hospice_diagnosis = models.CharField(max_length=256)
    patient_phone_number = models.CharField(max_length=12)
    attending_physician = models.CharField(max_length=181)
    referral_contact_name = models.CharField(max_length=181)
    referral_contact_number = models.CharField(max_length=12)
    comments = models.CharField(max_length=256)
    hospice_discussed = models.NullBooleanField(default=False)
    documents_faxed = models.NullBooleanField(default=False)
    need_representative = models.NullBooleanField(default=False)

