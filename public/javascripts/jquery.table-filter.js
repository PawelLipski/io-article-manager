/**
 * @preserve jQuery Plugin: Table Filter v0.2.3
 *
 * LICENSE: http://hail2u.mit-license.org/2009
 */

/*jslint indent: 2, browser: true, regexp: true */
/*global jQuery, $ */

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

            // Bind filtering function
            $("#" + id).delayBind("keyup", function (e) {
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

                    if (state) {
                        $(this).hide();
                    } else {
                        $(this).show();
                    }
                });
            }, 300);
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
    $('#data-table').each(function () {
        var currentPage = 0;
        var numPerPage = 3;

        var $table = $(this);

        $table.bind('repaginate', function () {
            // show all;
            $table.find('tbody tr').show();

            // hide before
            $table.find('tbody').find('tr:lt(' + (currentPage * numPerPage) + ')').hide();

            // hide after
            $table.find('tbody').find('tr:gt(' + ((currentPage + 1) * numPerPage - 1) + ')').hide();
        });

        var numRows = $table.find('tbody tr').length;
        var numPages = Math.ceil(numRows / numPerPage);

        var $pager = $('<ul class="pagination"></ul>');
        for (var page = 0; page < numPages; page++) {
            $('<li class="page-number"><a href="#">' + (page + 1) + '</a></li>')
                .bind('click', {'newPage': page}, function (event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                })
                .appendTo($pager).addClass('clickable');
        }
        $pager.find('span.page-number:first').addClass('active');
        $('#bottomrow').append($pager);
        //$pager.insertAfter($table);
        $table.trigger('repaginate');
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
    });

    $('#year-select, #journal-select, #issue-select').change(function () {
        window.location.replace("/authors/" + $("#journal-select").val() + "/" +
            $('#year-select').val() + "/" + $("#issue-select").val() )
    });

    $('#generate-report').click(function() {
        var authorsIds = [];

        $('#data-table tbody tr').each(function() {
            if ($(this).css("display") != 'none') {
                $(this).find("input").each(function() {
                    if ($(this).prop('checked')) {
                        authorsIds.push(parseInt($(this).attr('data-author-id')));
                    }
                });
            }
        });

        $.post({
            url: '/authors/generate',
            data: JSON.stringify(authorsIds),
            success: function(data) {
                
            }
        });
    });
});
