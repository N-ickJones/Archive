"""
Purpose: Creates Admin Page Selections
Author:  Nicholas Jones
Date:    06/10/2019
Version: 1.0
"""

from django.contrib import admin
from .models import *


class InquiryAdmin(admin.ModelAdmin):
    pass


class ReferralFormAdmin(admin.ModelAdmin):
    pass


class HospiceFormAdmin(admin.ModelAdmin):
    pass


admin.site.register(Inquiry, InquiryAdmin)
admin.site.register(ReferralForm, ReferralFormAdmin)
admin.site.register(HospiceForm, HospiceFormAdmin)
