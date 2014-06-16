/**
 * @preserve jQuery Plugin: Table Filter v0.2.3
 *
 * LICENSE: http://hail2u.mit-license.org/2009
 */

/*jslint indent: 2, browser: true, regexp: true */
/*global jQuery, $ */

function refreshSubmitButton() {
    $("button[type='submit']").attr("disabled", !$("input[class='selected-author']").is(":enabled:checked"));
}

(function ($) {
    "use strict";

    $.fn.addTableFilter = function (options) {
        var o = $.extend({}, $.fn.addTableFilter.defaults, options),
            tgt,
            id,
            label,
            input;

        if (this.is("table")) {
            // Generate ID
            if (!this.attr("id")) {
                this.attr({
                    id: "t-" + Math.floor(Math.random() * 99999999)
                });
            }
            tgt = this.attr("id");
            id = tgt + "-filtering";

            if (o.inputid == null) {
                // Build filtering form
                label = $("<label/>").attr({
                    "for": id
                }).append(o.labelText);
                input = $("<input type=\"search\"/>").attr({
                    id: id,
                    size: o.size
                }).on('click', function () {
                    $(this).keyup();
                });
                $("<p/>").addClass("formTableFilter").append(label).append(input).insertBefore(this);
            } else {
                id = o.inputid;
                input = $("#" + o.inputid).on('click', function () {
                    $(this).keyup();
                });
            }

            var filteringFunction = function (e) {
                var showOnlyVerified = $('#show-only-verified').prop('checked');
                var words = $(this).val().toLowerCase().split(" ");
                $("#" + tgt + " tbody tr").each(function () {
                    var s = $(this).html().toLowerCase().replace(/<.+?>/g, "").replace(/\s+/g, " "),
                        state = 0;
                    $.each(words, function () {
                        if (s.indexOf(this) < 0) {
                            state = 1;
                            return false; // break $.each()
                        }
                    });

                    var isNotVerified = $(this).find('.glyphicon-remove').length > 0;
                    var checkbox = $(this).find("input[class='selected-author']");
                    if (state || (showOnlyVerified && isNotVerified)) {
                        //$(this).hide();
                        checkbox.prop("disabled", true);
                    } else {
                        //$(this).show();
                        checkbox.prop("disabled", false);
                    }
                });
                refreshSubmitButton();
            };

            // Bind filtering function
            $("#" + id).delayBind("keyup", filteringFunction, 300);
            $("#" + id).bind("refreshFilter", filteringFunction);
        }

        return this;
    };

    $.fn.addTableFilter.defaults = {
        labelText: "Keyword(s): ",
        size: 32,
        inputid: null
    };

    $.fn.delayBind = function (type, data, func, timeout) {
        if ($.isFunction(data)) {
            timeout = func;
            func = data;
            data = undefined;
        }

        var self = this,
            wait = null,
            handler = function (e) {
                clearTimeout(wait);
                wait = setTimeout(function () {
                    func.apply(self, [$.extend({}, e)]);
                }, timeout);
            };

        return this.bind(type, data, handler);
    };
}(jQuery));

$(document).ready(function () {
    $("input[class='selected-author']").click(refreshSubmitButton);

    $('#show-only-verified').click(function() {
        $('#input-filter').trigger('refreshFilter');
    });

    $('#select-all-authors').click(function() {
        if (!$(this).prop('checked')) {
            $('#data-table tbody tr input').prop('checked', false);
        } else {
            $('#data-table tbody tr').each(function() {
                if ($(this).css("display") != 'none') {
                    $(this).find("input").prop('checked', true);
                }
            });
        }
        refreshSubmitButton();
    });

    $('#year-select, #journal-select, #issue-select').change(function () {
        window.location.replace("/manage-transfers/" + $("#journal-select").val() + "/" +
            $('#year-select').val() + "/" + $("#issue-select").val() )
    });

});
