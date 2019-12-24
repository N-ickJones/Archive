"""
Purpose: Creates Forms For User Input
Author:  Nicholas Jones
Date:    06/10/2019
Version: 1.0
"""


from django import forms
from django.contrib.auth.models import User
import datetime
import re


class SignupForm(forms.Form):
    username = forms.CharField(label='Username', max_length=50, widget=forms.TextInput())
    password1 = forms.CharField(label='Password', widget=forms.PasswordInput())
    password2 = forms.CharField(label='Confirm Password', widget=forms.PasswordInput())
    first_name = forms.CharField(label='First Name', max_length=30, widget=forms.TextInput())
    last_name = forms.CharField(label='Last Name', max_length=30, widget=forms.TextInput())
    email = forms.CharField(label='Email', widget=forms.EmailInput())
    account_type = forms.ChoiceField(choices=[
        ('patient', 'Patient'),
        ('patient_family_member', 'Patient Family Member'),
        ('staff', 'Staff')
    ], widget=forms.RadioSelect())

    def clean(self):
        data = self.cleaned_data
        if User.objects.filter(username=data['username']).exists():
            self.add_error('username', "Username {} (already in use).".format(data['username']))
        if data.get('password1') != data.get('password2'):
            self.add_error('password2', "Unable use Password (non-matching passwords)")
        else:
            password_error = []
            if not re.search(r'.{8,}', data.get('password1')):
                password_error.append('8 characters')
            if not re.search(r'[a-z]', data.get('password1')):
                password_error.append('a lowercase')
            if not re.search(r'[A-Z]', data.get('password1')):
                password_error.append('an uppercase')
            if not re.search(r'\d', data.get('password1')):
                password_error.append('a digit')
            if not re.search(r"""(?=.*[!@#$%^&*()\\[\]{}\-_+=~`|:;'"<>,./?])""", data.get('password1')):
                password_error.append('a special character')
            if password_error:
                message = 'Password needs'
                if len(password_error) == 1:
                    message = '{}{}.'.format(message, password_error[0])
                    self.add_error('password1', message)
                else:
                    for i in range(0, len(password_error)):
                        if not i == len(password_error) - 1:
                            message = '{}, {}'.format(message, password_error[i])
                        else:
                            message = '{}, and {}.'.format(message, password_error[i])
                    self.add_error('password1', message)
        if data.get('first_name').isalpha() is False:
            self.add_error('first_name', "Invalid First Name (letters only)")
        if data.get('last_name').isalpha() is False:
            self.add_error('last_name', "Invalid Last Name (letters only)")
        if User.objects.filter(email=data['email']).exists():
            self.add_error('email', "Email {} is already in use.".format(data['email']))
        if 'staff' in data.get('account_type'):
            if User.objects.filter(username=data['username']).exists() is False:
                if re.search(r'(?<!.)MedStaff_', data['username']) is False:
                    self.add_error('account_type', "Invalid Staff Credentials (Contact HR)")
            else:
                self.add_error('account_type', "Invalid Staff Credentials (Contact HR)")
        elif 'patient' in data.get('account_type'):
            if re.search(r'(?<!.)MedStaff_', data['username']):
                self.add_error('account_type', "Invalid Account Type")
        elif 'patient_family_member' in data.get('account_type'):
            if re.search(r'(?<!.)MedStaff_', data['username']):
                self.add_error('account_type', "Invalid Account Type")
        else:
            self.add_error('account_type', "Invalid Account Type")
        return data


class LoginForm(forms.Form):
    username = forms.CharField(label='Username', max_length=50, widget=forms.TextInput())
    password = forms.CharField(label='Password', widget=forms.PasswordInput())

    def clean(self):
        data = self.cleaned_data
        return data


class SearchForm(forms.Form):
    search_input = forms.CharField(label='Search', max_length=100, widget=forms.TextInput())

    def clean(self):
        data = self.cleaned_data
        return data


class UsernameRecoveryForm(forms.Form):
    email = forms.CharField(label='Email', max_length=100, widget=forms.EmailInput())

    def clean(self):
        data = self.cleaned_data
        if User.objects.filter(email=data.get('email')).exists() is False:
            self.add_error('email', "Invalid Email")
        return data


class PasswordRecoveryForm(forms.Form):
    email = forms.CharField(label='Email', max_length=100, widget=forms.EmailInput())

    def clean(self):
        data = self.cleaned_data
        if User.objects.filter(email=data.get('email')).exists() is False:
            self.add_error('email', "Invalid Email")
        return data


class PasswordResetForm(forms.Form):
    password1 = forms.CharField(label='New Password', widget=forms.PasswordInput())
    password2 = forms.CharField(label='Confirm New Password', widget=forms.PasswordInput())

    def clean(self):
        data = self.cleaned_data
        if data.get('password1') != data.get('password2'):
            self.add_error('password2', "passwords do not match !")
        return data


class PasswordChangeForm(forms.Form):
    password1 = forms.CharField(label='Current Password', widget=forms.PasswordInput())
    password2 = forms.CharField(label='New Password', widget=forms.PasswordInput())
    password3 = forms.CharField(label='Confirm New Password', widget=forms.PasswordInput())

    def clean(self):
        data = self.cleaned_data
        if data.get('password2') != data.get('password3'):
            self.add_error('password3', "Unable use Password (non-matching passwords)")
        else:
            password_error = []
            if not re.search(r'.{8,}', data.get('password2')):
                password_error.append('8 characters')
            if not re.search(r'[a-z]', data.get('password2')):
                password_error.append('a lowercase')
            if not re.search(r'[A-Z]', data.get('password2')):
                password_error.append('an uppercase')
            if not re.search(r'\d', data.get('password2')):
                password_error.append('a digit')
            if not re.search(r"""(?=.*[!@#$%^&*()\\[\]{}\-_+=~`|:;'"<>,./?])""", data.get('password2')):
                password_error.append('a special character')
            if password_error:
                message = 'Password needs'
                if len(password_error) == 1:
                    message = '{}{}.'.format(message, password_error[0])
                    self.add_error('password3', message)
                else:
                    for i in range(0, len(password_error)):
                        if not i == len(password_error) - 1:
                            message = '{}, {}'.format(message, password_error[i])
                        else:
                            message = '{}, and {}.'.format(message, password_error[i])
                    self.add_error('password3', message)
        return data


class SettingsForm(forms.Form):

    def __init__(self, *args, **kwargs):
        self.request = kwargs.pop('request')
        super(SettingsForm, self).__init__(*args, **kwargs)
        print(self.request.user.first_name)
        self.fields['first_name'] = forms.CharField(label='Change First Name (' + self.request.user.first_name + ')',
                                                    max_length=30,
                                                    widget=forms.TextInput())
        self.fields['last_name'] = forms.CharField(label='Change Last Name (' + self.request.user.last_name + ')',
                                                   max_length=150,
                                                   widget=forms.TextInput())
        self.fields['email'] = forms.CharField(label='Change Email (' + self.request.user.email + ')',
                                               max_length=150,
                                               widget=forms.EmailInput())
    first_name = forms.CharField()
    last_name = forms.CharField()
    email = forms.EmailInput()

    def clean(self):
        data = self.cleaned_data
        return data


class Blank(forms.Form):
    pass


class InquiryForm(forms.Form):
    name = forms.CharField(label='Name', max_length=50, widget=forms.TextInput())
    phone = forms.CharField(label='Phone', max_length=12, widget=forms.TextInput())
    email = forms.CharField(label='Email', max_length=100, widget=forms.TextInput())
    questions = forms.CharField(label='Questions', max_length=300, widget=forms.TextInput())

    def clean(self):
        data = self.cleaned_data
        return data


class PatientRegistration(forms.Form):
    info = forms.CharField(label='info', max_length=50, widget=forms.TextInput())

    def clean(self):
        data = self.cleaned_data
        return data


class ReferralFormInput(forms.Form):
    # username auto filled
    first_name = forms.CharField(label='First Name', max_length=30, widget=forms.TextInput())
    last_name = forms.CharField(label='Last Name', max_length=150, widget=forms.TextInput())
    ssn = forms.CharField(label='Social Security Number', max_length=11, widget=forms.TextInput())
    dob = forms.DateField(label="Date of Birth", initial=datetime.datetime.now(),
                          widget=forms.SelectDateWidget(years=range(datetime.datetime.now().year-150,
                                                                    datetime.datetime.now().year+1)))
    gender = forms.ChoiceField(label='Gender', choices=[
        ('Male', 'Male'),
        ('Female', 'Female'),
        ('Other', 'Other')
    ])
    address = forms.CharField(label='Address', max_length=100, widget=forms.TextInput())
    city = forms.CharField(label='City', max_length=30, widget=forms.TextInput())
    state = forms.ChoiceField(label='State', choices=[
        ('Alabama', 'Alabama'),
        ('Alaska', 'Alaska'),
        ('Arizona', 'Arizona'),
        ('Arkansas', 'Arkansas'),
        ('California', 'California'),
        ('Colorado', 'Colorado'),
        ('Connecticut', 'Connecticut'),
        ('Delaware', 'Delaware'),
        ('District Of Columbia', 'District Of Columbia'),
        ('Florida', 'Florida'),
        ('Georgia', 'Georgia'),
        ('Hawaii', 'Hawaii'),
        ('Idaho', 'Idaho'),
        ('Illinois', 'Illinois'),
        ('Indiana', 'Indiana'),
        ('Iowa', 'Iowa'),
        ('Kansas', 'Kansas'),
        ('Kentucky', 'Kentucky'),
        ('Louisiana', 'Louisiana'),
        ('Maine', 'Maine'),
        ('Maryland', 'Maryland'),
        ('Massachusetts', 'Massachusetts'),
        ('Michigan', 'Michigan'),
        ('Minnesota', 'Minnesota'),
        ('Mississippi', 'Mississippi'),
        ('Missouri', 'Missouri'),
        ('Montana', 'Montana'),
        ('Nebraska', 'Nebraska'),
        ('Nevada', 'Nevada'),
        ('New Hampshire', 'New Hampshire'),
        ('New Jersey', 'New Jersey'),
        ('New Mexico', 'New Mexico'),
        ('New York', 'New York'),
        ('North Carolina', 'North Carolina'),
        ('North Dakota', 'North Dakota'),
        ('Ohio', 'Ohio'),
        ('Oklahoma', 'Oklahoma'),
        ('Oregon', 'Oregon'),
        ('Pennsylvania', 'Pennsylvania'),
        ('Rhode Island', 'Rhode Island'),
        ('South Carolina', 'South Carolina'),
        ('South Dakota', 'South Dakota'),
        ('Tennessee', 'Tennessee'),
        ('Texas', 'Texas'),
        ('Utah', 'Utah'),
        ('Vermont', 'Vermont'),
        ('Virginia', 'Virginia'),
        ('Washington', 'Washington'),
        ('West Virginia', 'West Virginia'),
        ('Wisconsin', 'Wisconsin'),
        ('Wyoming', 'Wyoming'),
        ('American Samoa', 'American Samoa'),
        ('Guam', 'Guam'),
        ('Northern Mariana Islands', 'Northern Mariana Islands'),
        ('Puerto Rico', 'Puerto Rico'),
        ('US Minor Outlying Islands', 'US Minor Outlying Islands'),
        ('Virgin Islands', 'Virgin Islands'),
        ('Armed Forces Americas(AA)', 'Armed Forces Americas'),
        ('Armed Forces Pacific(AP)', 'Armed Forces Pacific'),
        ('Armed Forces Others(AE)', 'Armed Forces Others'),
    ])
    zip = forms.CharField(label='Zip', max_length=7, widget=forms.TextInput())
    alt_first_name = forms.CharField(label='Alternate Contacts First Name', max_length=30, widget=forms.TextInput())
    alt_last_name = forms.CharField(label='Alternate Contacts Last Name', max_length=150, widget=forms.TextInput())
    alt_phone = forms.CharField(label='Alternate Contact Phone', max_length=12, widget=forms.TextInput())
    flu_vaccine = forms.DateField(label="Last Flu Vaccination", initial=datetime.datetime.now(),
                                  widget=forms.SelectDateWidget(years=range(datetime.datetime.now().year-10,
                                                                            datetime.datetime.now().year+1)))
    # Referral Date auto filled
    primary_care_physician = forms.CharField(label='Primary Care Physician', max_length=181, widget=forms.TextInput())
    insurance_information = forms.CharField(label='Insurance Information', max_length=256, widget=forms.Textarea())
    diagnosis = forms.CharField(label='Diagnosis', max_length=256, widget=forms.Textarea())

    def clean(self):
        data = self.cleaned_data
        if data.get('first_name').isalpha() is False:
            self.add_error('first_name', "Invalid First Name (letters only)")
        if data.get('last_name').isalpha() is False:
            self.add_error('first_name', "Invalid Last Name (letters only)")
        if data.get('ssn').isnumeric() is False:
            self.add_error('ssn', "Invalid Social Security Number(numbers only)")
        if data.get('zip').isnumeric() is False:
            self.add_error('zip', "Invalid Zip Code (numbers only)")
        if data.get('alt_first_name').isalpha() is False:
            self.add_error('alt_first_name', "Invalid Alternate Contact First Name(letters only)")
        if data.get('alt_last_name').isalpha() is False:
            self.add_error('alt_last_name', "Invalid Alternate Contact Last Name(letters only)")
        if data.get('alt_phone').isnumeric() is False:
            self.add_error('alt_phone', "Invalid Phone Number (numbers only)")
        return data


class HospiceFormInput(forms.Form):
    first_name = forms.CharField(label='First Name', max_length=30, widget=forms.TextInput())
    last_name = forms.CharField(label='Last Name', max_length=150, widget=forms.TextInput())
    gender = forms.ChoiceField(label='Gender', choices=[
        ('Male', 'Male'),
        ('Female', 'Female'),
        ('Other', 'Other')
    ])
    dob = forms.DateField(label="Date of Birth", initial=datetime.datetime.now(), widget=forms.SelectDateWidget(
        years=range(datetime.datetime.now().year - 150, datetime.datetime.now().year + 1)))
    address = forms.CharField(label='Address', max_length=100, widget=forms.TextInput())
    city = forms.CharField(label='City', max_length=30, widget=forms.TextInput())
    state = forms.ChoiceField(label='State', choices=[
        ('Alabama', 'Alabama'),
        ('Alaska', 'Alaska'),
        ('Arizona', 'Arizona'),
        ('Arkansas', 'Arkansas'),
        ('California', 'California'),
        ('Colorado', 'Colorado'),
        ('Connecticut', 'Connecticut'),
        ('Delaware', 'Delaware'),
        ('District Of Columbia', 'District Of Columbia'),
        ('Florida', 'Florida'),
        ('Georgia', 'Georgia'),
        ('Hawaii', 'Hawaii'),
        ('Idaho', 'Idaho'),
        ('Illinois', 'Illinois'),
        ('Indiana', 'Indiana'),
        ('Iowa', 'Iowa'),
        ('Kansas', 'Kansas'),
        ('Kentucky', 'Kentucky'),
        ('Louisiana', 'Louisiana'),
        ('Maine', 'Maine'),
        ('Maryland', 'Maryland'),
        ('Massachusetts', 'Massachusetts'),
        ('Michigan', 'Michigan'),
        ('Minnesota', 'Minnesota'),
        ('Mississippi', 'Mississippi'),
        ('Missouri', 'Missouri'),
        ('Montana', 'Montana'),
        ('Nebraska', 'Nebraska'),
        ('Nevada', 'Nevada'),
        ('New Hampshire', 'New Hampshire'),
        ('New Jersey', 'New Jersey'),
        ('New Mexico', 'New Mexico'),
        ('New York', 'New York'),
        ('North Carolina', 'North Carolina'),
        ('North Dakota', 'North Dakota'),
        ('Ohio', 'Ohio'),
        ('Oklahoma', 'Oklahoma'),
        ('Oregon', 'Oregon'),
        ('Pennsylvania', 'Pennsylvania'),
        ('Rhode Island', 'Rhode Island'),
        ('South Carolina', 'South Carolina'),
        ('South Dakota', 'South Dakota'),
        ('Tennessee', 'Tennessee'),
        ('Texas', 'Texas'),
        ('Utah', 'Utah'),
        ('Vermont', 'Vermont'),
        ('Virginia', 'Virginia'),
        ('Washington', 'Washington'),
        ('West Virginia', 'West Virginia'),
        ('Wisconsin', 'Wisconsin'),
        ('Wyoming', 'Wyoming'),
        ('American Samoa', 'American Samoa'),
        ('Guam', 'Guam'),
        ('Northern Mariana Islands', 'Northern Mariana Islands'),
        ('Puerto Rico', 'Puerto Rico'),
        ('US Minor Outlying Islands', 'US Minor Outlying Islands'),
        ('Virgin Islands', 'Virgin Islands'),
        ('Armed Forces Americas(AA)', 'Armed Forces Americas'),
        ('Armed Forces Pacific(AP)', 'Armed Forces Pacific'),
        ('Armed Forces Others(AE)', 'Armed Forces Others'),
    ])
    zip = forms.CharField(label='Zip', max_length=7, widget=forms.TextInput())
    hospice_diagnosis = forms.CharField(label='Hospice_Diagnosis', max_length=256, widget=forms.Textarea())
    patient_phone_number = forms.CharField(label='Patient Phone Number', max_length=12, widget=forms.TextInput())
    attending_physician = forms.CharField(label='Attending Physician', max_length=181, widget=forms.TextInput())
    referral_contact_name = forms.CharField(label='Referral Contact Name', max_length=181, widget=forms.TextInput())
    referral_contact_number = forms.CharField(label='Referral Contact Phone', max_length=12, widget=forms.TextInput())
    comments = forms.CharField(label='Comments', max_length=256, widget=forms.Textarea())
    hospice_discussed = forms.BooleanField(label='Has hospice been discussed with patient?',
                                           initial=False,
                                           widget=forms.NullBooleanSelect,
                                           required=False)
    documents_faxed = forms.BooleanField(label='Do you need to fax supporting documentation?',
                                         initial=False,
                                         widget=forms.NullBooleanSelect,
                                         required=False)
    need_representative = forms.BooleanField(label='Do you need a representative to contact you?',
                                             initial=False,
                                             widget=forms.NullBooleanSelect,
                                             required=False)

    def clean(self):
        data = self.cleaned_data
        if data.get('first_name').isalpha() is False:
            self.add_error('first_name', "Invalid First Name (letters only)")
        if data.get('last_name').isalpha() is False:
            self.add_error('first_name', "Invalid Last Name (letters only)")
        if data.get('zip').isnumeric() is False:
            self.add_error('zip', "Invalid Zip Code (numbers only)")
        if data.get('patient_phone_number').isnumeric() is False:
            self.add_error('patient_phone_number', "Invalid Phone Number (numbers only)")
        if data.get('referral_contact_number').isnumeric() is False:
            self.add_error('referral_contact_number', "Invalid Phone Number (numbers only)")
        return data
