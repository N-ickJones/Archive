"""
Purpose: Creates The Visual Views For Web Pages
Author:  Nicholas Jones
Date:    06/10/2019
Version: 1.0
"""


from .forms import *
from .tokens import account_activation_token
from .models import *
from core_settings import settings
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import User
from django.contrib.sites.shortcuts import get_current_site
from django.core.mail import send_mail
from django.shortcuts import render, redirect
from django.template.loader import render_to_string
from django.utils import timezone
from django.utils.encoding import force_bytes, force_text
from django.utils.http import urlsafe_base64_encode, urlsafe_base64_decode


# Page Settings
website_name = 'Legacy Of Love Hospice'
page_base = '.Base-Content/base.html'
page_admin = 'Admin/base.html'
page_content = {
    'website_name': website_name,
    'submenu_top': 'SubMenu/Default/submenu_bottom.html',
    'submenu_bottom': 'SubMenu/Default/submenu_top.html',
    'content_top': '.Base-Content/base_content_top.html',
    'content_bottom': '.Base-Content/base.html',
    'login_form': LoginForm(),
    'search_form': SearchForm(),
}
# Email Settings
mail = True
website_email = website_name + '@mail.com'


# Customer Pages
def index_view(request):
    page_content['content_top'] = '.Base-Content/base_content_top.html'
    page_content['content_bottom'] = '.Base-Content/base_content_bottom.html'
    page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
    page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
    return render(request, page_base, page_content)


def about_view(request):
    if request.method == 'GET':
        page_content['content_top'] = 'About/about_top.html'
        page_content['content_bottom'] = 'About/about_bottom.html'
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        return render(request, page_base, page_content)


def services_view(request):
    if request.method == 'GET':
        page_content['content_top'] = 'Services/services_top.html'
        page_content['content_bottom'] = 'Services/services_bottom.html'
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        return render(request, page_base, page_content)


def inquires_view(request):
    if request.method == 'GET':
        form = InquiryForm()
        page_content['content_top'] = 'Inquires/inquires_top.html'
        page_content['content_bottom'] = 'Inquires/inquires_bottom.html'
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        page_content['form'] = form
        page_content['content_form'] = 'Inquires/inquiry_form.html'
        page_content['form_title'] = '{} Inquiry Form'.format(website_name)
        return render(request, page_base, page_content)
    elif request.method == 'POST':
        form = InquiryForm(request.POST)
        if form.is_valid():
            inq = Inquiry()
            inq.name = form.cleaned_data['name']
            inq.email = form.cleaned_data['email']
            inq.phone = form.cleaned_data['phone']
            inq.questions = form.cleaned_data['questions']
            inq.date = datetime.datetime.now()
            inq.save()
            page_content['content_top'] = 'Inquires/complete.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            return render(request, page_base, page_content)
        else:
            pass
    else:
        return redirect('/')


def contact_view(request):
    if request.method == 'GET':
        page_content['content_top'] = 'Contact/contact_top.html'
        page_content['content_bottom'] = 'Contact/contact_bottom.html'
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        return render(request, page_base, page_content)
# End Customer Pages


# Start Login Functions
def search_view(request):
    if request.method == 'POST':
        form = SearchForm(request.POST)
        if form.is_valid():
            return redirect('/')
        else:
            page_content['search_alert'] = 'Search Failed'
            return redirect(request.POST.get('next'))
    else:
        return redirect('/')


def login_view(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            user = authenticate(username=form.cleaned_data['username'],
                                password=form.cleaned_data['password'])
            if user is not None:
                login(request, user)
                page_content['login_alert'] = ''
                return redirect(request.POST.get('next'))
            else:
                page_content['login_alert'] = 'Invalid Credentials'
                return redirect(request.POST.get('next'))
        else:
            page_content['login_alert'] = 'Invalid Credentials'
            return redirect(request.POST.get('next'))
    else:
        return redirect('/')


def logout_view(request):
    if request.method == 'POST':
        logout(request)
        return redirect('/')
    else:
        return redirect('/')


def signup_view(request):
    if request.method == 'POST':
        form = SignupForm(request.POST)
        if form.is_valid():
            user = User.objects.create_user(form.cleaned_data['username'],
                                            form.cleaned_data['email'],
                                            form.cleaned_data['password1'])
            user.first_name = form.cleaned_data['first_name']
            user.last_name = form.cleaned_data['last_name']
            user.last_login = timezone.now()
            user.is_superuser = False
            user.is_active = False  # Cannot Login Until Email Verification
            user.is_staff = False
            user.save()
            # Start Email
            current_site = get_current_site(request)
            subject = 'Activate your ' + website_name + ' account.'
            message = render_to_string('Registration/email_confirm.html', {
                'user': user,
                'domain': current_site.domain,
                'uid': urlsafe_base64_encode(force_bytes(user.pk)).decode(),
                'token': account_activation_token.make_token(user),
            })
            from_email = website_email
            to_list = [form.cleaned_data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            # End Email
            page_content['content_top'] = 'Registration/email_check.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = '{} Account Form'.format(website_name)
            return render(request, page_base, page_content)
    elif request.method == 'GET':
        form = SignupForm()
        page_content['content_top'] = 'Registration/default_form.html'
        page_content['content_bottom'] = ''
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        page_content['form'] = form
        page_content['form_title'] = '{} Account Form'.format(website_name)
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Signup_view')


def activate_view(request, uidb64,  token):
    try:
        uid = force_text(urlsafe_base64_decode(uidb64))
        user = User.objects.get(pk=uid)
    except(TypeError, ValueError, OverflowError, User.DoesNotExist):
        user = None
    if user is not None and account_activation_token.check_token(user, token):
        user.is_active = True
        user.save()
        login(request, user)
        page_content['content_top'] = 'Registration/email_confirm_complete.html'
        page_content['content_bottom'] = ''
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        return redirect('email_confirm_complete')
    else:
        page_content['content_top'] = 'Registration/invalid_activation_code.html'
        return render(request, page_base, page_content)


def email_confirm_complete_view(request):
    page_content['content_top'] = 'Registration/email_confirm_complete.html'
    page_content['content_bottom'] = ''
    page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
    page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
    return render(request, page_base, page_content)


def username_recovery_view(request):
    if request.method == 'POST':
        form = UsernameRecoveryForm(request.POST)
        if form.is_valid():
            user = User.objects.get(email=form.data['email'])
            current_site = get_current_site(request)
            subject = 'Activate your ' + website_name + ' account.'
            message = render_to_string('Registration/email_username_recovery.html', {
                'user': user,
                'domain': current_site.domain,
            })
            from_email = website_email
            to_list = [form.data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            page_content['content_top'] = 'Registration/username_sent.html'
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = '{} Account Form'.format(website_name)
            page_content['username_form_error'] = 'Invalid Email'
            return render(request, page_base, page_content)
    elif request.method == 'GET':
        form = UsernameRecoveryForm()
        page_content['content_top'] = 'Registration/default_form.html'
        page_content['content_bottom'] = ''
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        page_content['form'] = form
        page_content['form_title'] = 'Username Recovery'
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Username Recovery')


def password_reset_view(request):
    if request.method == 'POST':
        form = PasswordRecoveryForm(request.POST)
        if form.is_valid():
            user = User.objects.get(email=form.data['email'])
            current_site = get_current_site(request)
            subject = website_name + ' Password Recovery'
            message = render_to_string('Registration/password_reset_email.html', {
                'user': user,
                'protocol': 'http',
                'domain': current_site.domain,
                'uid': urlsafe_base64_encode(force_bytes(user.pk)).decode(),
                'token': account_activation_token.make_token(user),
            })
            from_email = website_email
            to_list = [form.data['email'], settings.EMAIL_HOST_USER]
            if mail:
                send_mail(subject, message, from_email, to_list, fail_silently=True)
            page_content['content_top'] = 'Registration/password_reset_done.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            return render(request, page_base, page_content)
        else:
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Recovery'
            page_content['username_form_error'] = 'Invalid Email'
            return render(request, page_base, page_content)

    elif request.method == 'GET':
        form = PasswordRecoveryForm()
        page_content['content_top'] = 'Registration/default_form.html'
        page_content['content_bottom'] = ''
        page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
        page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
        page_content['form'] = form
        page_content['form_title'] = 'Password Recovery'
        return render(request, page_base, page_content)
    else:
        print('Configure a ' + request.method + ' Request Method in Password Recovery')


def password_reset_confirm_view(request, uidb64,  token):
    if request.method == 'POST':
        form = PasswordResetForm()
        if form.is_valid:
            try:
                uid = force_text(urlsafe_base64_decode(uidb64))
                user = User.objects.get(pk=uid)
            except(TypeError, ValueError, OverflowError, User.DoesNotExist):
                user = None
            if user is not None:
                user.set_password(form.cleaned_data['password2'])
                user.save()
                return redirect('/password_reset_complete')
            else:
                return redirect('/invalid_activation_code')
        else:
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Recovery'
            return render(request, page_base, page_content)
    if request.method == 'GET':
        try:
            uid = force_text(urlsafe_base64_decode(uidb64))
            user = User.objects.get(pk=uid)
        except(TypeError, ValueError, OverflowError, User.DoesNotExist):
            user = None
        if user is not None and account_activation_token.check_token(user, token):
            form = PasswordResetForm()
            page_content['email'] = user.email
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = 'Password Reset'
            return render(request, page_base, page_content)
        else:
            return redirect('/invalid_activation_code')
    else:
        print('Configure a ' + request.method + ' Request Method in Password Reset Confirm')


def password_reset_complete_view(request):
    page_content['content_top'] = 'Registration/password_reset_complete.html'
    page_content['content_bottom'] = ''
    page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
    page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
    return render(request, page_base, page_content)
# End Login Functions


# Start User Profiles
def profile_view(request):
    # Customer Profile
    if request.user.is_authenticated & request.user.is_staff is False:
        if request.method == 'POST':
            return redirect('/')
        elif request.method == 'GET':
            form = PatientRegistration()
            page_content['content_top'] = 'Registration/profile.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
            page_content['progress'] = '25'
            page_content['form'] = form
            page_content['form_title'] = '{} User Profile'.format(website_name)
            return render(request, page_base, page_content)

    # Medical Staff Profile
    elif request.user.is_authenticated & request.user.is_staff:
        if request.method == 'POST':
            return redirect('/')
        elif request.method == 'GET':
            form = PatientRegistration()
            page_content['content_top'] = 'Registration/profile.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
            page_content['progress'] = '25'
            page_content['form'] = form
            page_content['form_title'] = '{} User Profile'.format(website_name)
            return render(request, page_base, page_content)
    else:
        return redirect('/')


def setting_view(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            return render(request, page_base, page_content)
        elif request.method == 'GET':
            form = SettingsForm(request=request)
            # page_content['content_top'] = 'Registration/settings_form.html'
            page_content['content_top'] = 'Registration/default_form.html'
            # page_content['content_bottom'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
            page_content['form'] = form
            page_content['form_title'] = '{} User Settings'.format(website_name)
            return render(request, page_base, page_content)
    else:
        return redirect('/')


def password_change_view(request):
    if request.user.is_authenticated:
        if request.method == 'POST':
            form = PasswordChangeForm(request.POST)
            if form.is_valid():
                user = authenticate(username=request.user,
                                    password=form.cleaned_data['password1'])
                if user is not None:
                    user.set_password(form.cleaned_data['password3'])
                    user.save()
                    login(request, user)
                    return redirect('/password_change_complete')
                else:
                    form.add_error('password1', "Invalid Password")
                    page_content['content_top'] = 'Registration/default_form.html'
                    page_content['content_bottom'] = ''
                    page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
                    page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
                    page_content['form'] = form
                    page_content['form_title'] = 'Password Recovery'
                    return render(request, page_base, page_content)
            else:
                page_content['content_top'] = 'Registration/default_form.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
                page_content['form'] = form
                page_content['form_title'] = 'Password Recovery'
                return render(request, page_base, page_content)
        elif request.method == 'GET':
            if request.user.is_authenticated:
                form = PasswordChangeForm()
                page_content['content_top'] = 'Registration/default_form.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
                page_content['form'] = form
                page_content['form_title'] = 'Password Change'
                return render(request, page_base, page_content)
            else:
                return redirect('/')
        else:
            print('Configure a ' + request.method + ' Request Method in Password Change Form')
    else:
        return redirect('/')


def password_change_complete_view(request):
    page_content['content_top'] = 'Registration/password_change_complete.html'
    page_content['content_bottom'] = ''
    page_content['submenu_top'] = 'SubMenu/Default/submenu_top.html'
    page_content['submenu_bottom'] = 'SubMenu/Default/submenu_bottom.html'
    return render(request, page_base, page_content)
# End User Profiles


# Start Online Forms
def referral_form_view(request):
    if request.user.is_authenticated:
        if request.method == 'GET':
            form = ReferralFormInput()
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
            page_content['form'] = form
            page_content['content_form'] = 'Inquires/inquiry_form.html'
            page_content['form_title'] = '{} Referral Form'.format(website_name)
            return render(request, page_base, page_content)
        elif request.method == 'POST':
            form = ReferralFormInput(request.POST)
            if form.is_valid():
                ref = ReferralForm()
                ref.username = request.user.username
                ref.patient_first_name = form.cleaned_data['first_name']
                ref.patient_last_name = form.cleaned_data['last_name']
                ref.social_security_number = form.cleaned_data['ssn']
                ref.date_of_birth = form.cleaned_data['dob']
                ref.gender = form.cleaned_data['gender']
                ref.address = form.cleaned_data['address']
                ref.city = form.cleaned_data['city']
                ref.state = form.cleaned_data['state']
                ref.zip = form.cleaned_data['zip']
                ref.alt_contact_first_name = form.cleaned_data['alt_first_name']
                ref.alt_contact_last_name = form.cleaned_data['alt_last_name']
                ref.alt_contact_number = form.cleaned_data['alt_phone']
                ref.flu_vaccine = form.cleaned_data['flu_vaccine']
                ref.referral_date = timezone.now()
                ref.primary_care_physician = form.cleaned_data['primary_care_physician']
                ref.insurance_information = form.cleaned_data['insurance_information']
                ref.diagnosis = form.cleaned_data['diagnosis']
                ref.save()
                page_content['content_top'] = 'Forms/referral_complete.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
                return render(request, page_base, page_content)
            else:
                page_content['content_top'] = 'Registration/default_form.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
                page_content['form'] = form
                page_content['content_form'] = 'Inquires/inquiry_form.html'
                page_content['form_title'] = '{} Referral Form'.format(website_name)
                return render(request, page_base, page_content)
        else:
            return redirect('/')
    else:
        return redirect('/')


def hospice_form_view(request):
    if request.user.is_authenticated:
        if request.method == 'GET':
            form = HospiceFormInput()
            page_content['content_top'] = 'Registration/default_form.html'
            page_content['content_bottom'] = ''
            page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
            page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
            page_content['form'] = form
            page_content['content_form'] = 'Inquires/inquiry_form.html'
            page_content['form_title'] = '{} Hospice Form'.format(website_name)
            return render(request, page_base, page_content)
        elif request.method == 'POST':
            form = HospiceFormInput(request.POST)
            if form.is_valid():
                hos = HospiceForm()
                hos.username = request.user.username
                hos.patient_first_name = form.cleaned_data['first_name']
                hos.patient_last_name = form.cleaned_data['last_name']
                hos.gender = form.cleaned_data['gender']
                hos.date_of_birth = form.cleaned_data['dob']
                hos.address = form.cleaned_data['address']
                hos.city = form.cleaned_data['city']
                hos.state = form.cleaned_data['state']
                hos.zip = form.cleaned_data['zip']
                hos.hospice_diagnosis = form.cleaned_data['hospice_diagnosis']
                hos.patient_phone_number = form.cleaned_data['patient_phone_number']
                hos.attending_physician = form.cleaned_data['attending_physician']
                hos.referral_contact_name = form.cleaned_data['referral_contact_name']
                hos.referral_contact_number = form.cleaned_data['referral_contact_number']
                hos.comments = form.cleaned_data['comments']
                hos.hospice_discussed = form.cleaned_data['hospice_discussed']
                hos.documents_faxed = form.cleaned_data['documents_faxed']
                hos.need_representative = form.cleaned_data['need_representative']
                hos.save()
                page_content['content_top'] = 'Forms/hospice_complete.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
                return render(request, page_base, page_content)
            else:
                page_content['content_top'] = 'Registration/default_form.html'
                page_content['content_bottom'] = ''
                page_content['submenu_top'] = 'SubMenu/User_Account/submenu_top.html'
                page_content['submenu_bottom'] = 'SubMenu/User_Account/submenu_bottom.html'
                page_content['form'] = form
                page_content['content_form'] = 'Inquires/inquiry_form.html'
                page_content['form_title'] = '{} Hospice Form'.format(website_name)
                return render(request, page_base, page_content)
        else:
            return redirect('/')
    else:
        return redirect('/')
