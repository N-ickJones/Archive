"""
Purpose: Creates Tokens For Verification Purposes
Author:  Nicholas Jones
Date:    06/10/2019
Version: 1.0
"""

from django.contrib.auth.tokens import PasswordResetTokenGenerator
from django.utils import six


# Token Generators
class TokenGenerator(PasswordResetTokenGenerator):

    def _make_hash_value(self, user, timestamp):
        return (
            six.text_type(user.pk) + six.text_type(timestamp) +
            six.text_type(user.is_active)
        )


account_activation_token = TokenGenerator()
