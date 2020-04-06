<p class="calculatorCalibre">Monthly Payments</p>
<p id="monthlyPayment"><g:formatNumber  number="${monthlyPayment}" type="currency" currencyCode="USD" /></p>
<div class="calculatorCalibre">
    <span class="calculatorTotal">Total Principal Paid</span>
    <span id="total" class="calculatorTotalNum"><g:formatNumber  number="${mortgageamount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></span>
</div>
<hr/>
<div class="calculatorCalibre">
    <span class="calculatorTotal">Total Interest Paid</span>

    <span id="totalinterest" class="calculatorTotalNum"><g:formatNumber  number="${totalInterest}" type="currency" currencyCode="USD" /></span>
</div>


