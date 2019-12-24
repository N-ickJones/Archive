using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;

namespace KarbonWebForms
{
    public static class Validators
    {
        private static readonly Regex charOnly = new Regex(@"^[a-zA-Z]+$", RegexOptions.Compiled);
        private static readonly Regex emailValid = new Regex(@"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$", RegexOptions.Compiled);
        private static readonly Regex usernameValid = new Regex(@"^[a-zA-Z0-9]([._](?![._])|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$", RegexOptions.Compiled);
        private static readonly Regex passwordValid = new Regex(@"^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$", RegexOptions.Compiled);
        public static readonly string LineBreak = "&#013;&#010;";
        public static readonly string verfToken = "38749384";
        public static readonly string passwordRecoveryToken = "83666231";


        public static bool IsValidName(string input)
        {
            return !charOnly.IsMatch(input);
        }

        public static bool IsValidEmail(string input)
        {
            return !emailValid.IsMatch(input);
        }

        public static bool IsValidUsername(string input)
        {
            return !usernameValid.IsMatch(input);
        }

        public static bool IsValidPassword(string input)
        {
            return !passwordValid.IsMatch(input);
        }

        public static string PreFormatError(string input)
        {
            return $"<pre class=\"text-danger\">{input}</pre>";
        }


    }
}