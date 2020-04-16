<div class="row">
    <label  for="amount">
        <span>Mortgage amount</span>
    </label>
    <input type="text" id="amount" name="amount" class="amortizationInput" placeholder="$" value="${amount}">
    <div class="message_error">
        <g:if test="${flash.message}">
            <g:renderErrors bean="${flash.message}" as="list" field="amount"/>
        </g:if>
    </div>
</div>
<div class="row">
    <label  for="years">
        <span>Mortgage term in years</span>
    </label>
    <input type="text" id="years" name="years" class="amortizationInput" value="${months/12}">
    <div class="message_error">
        <g:if test="${flash.message}">
            <g:renderErrors bean="${flash.message}" as="list" field="years"/>
        </g:if>
    </div>
</div>
<div class="row">

    <label  for="months">
        <span><span>Or<br></span>Term in months</span>
    </label>
    <input type="text" name="months" id="months" class="amortizationInput" value="${months}">
    <div class="message_error">
        <g:if test="${flash.message}">
            <g:renderErrors bean="${flash.message}" as="list" field="months"/>
        </g:if>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <label  for="interest">
            <span>Interest rate per year</span>
        </label>
        <input type="number" name="interest" step="0.01" min="0" max="100" id="interest" class="amortizationInput" placeholder="%" value="${interest}">
        <div class="message_error">
            <g:if test="${flash.message}">
                <g:renderErrors bean="${flash.message}" as="list" field="interest"/>
            </g:if>
        </div>
    </div>
    <div class="col-md-6">
        <a style="color: white" class="btn amortizationButton" onclick="calculateAmortization()" id="calculate">Calculate</a>
    </div>
</div>