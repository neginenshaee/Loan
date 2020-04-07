<div class="row">
    <label  for="mortgageamount">
        <span>Mortgage amount</span>
    </label>
    <input type="text" id="mortgageamount" name="mortgageamount" class="amortizationInput" placeholder="$" value="${amount}">
</div>
<div class="row">
    <label  for="years">
        <span>Mortgage term in years</span>
    </label>
    <input type="text" id="years" name="years" class="amortizationInput" value="${months/12}">

</div>
<div class="row">

    <label  for="month">
        <span><span>Or<br></span>Term in months</span>
    </label>
    <input type="text" name="month" id="month" class="amortizationInput" value="${months}">

</div>
<div class="row">
    <div class="col-md-6">
        <label  for="interest">
            <span>Interest rate per year</span>
        </label>
        <input type="number" name="interest" step="0.01" min="0" max="100" id="interest" class="amortizationInput" placeholder="%" value="${interest}">
    </div>
    <div class="col-md-6">
        <a style="color: white" class="btn amortizationButton" onclick="calculateAmortization()" id="calculate">Calculate</a>
    </div>
</div>