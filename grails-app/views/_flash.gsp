<g:if test="${flash.message}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi-check-circle"></i> ${flash.message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</g:if>
<g:if test="${flash.error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi-exclamation-triangle"></i> ${flash.error}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</g:if>