/* 
 * Copyright (C) 2015 GISNET
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

$(document).ready(function () {
    pageSetUp();

    /*
     * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
     */
     $('#entidadId').change(function() {
        $.getJSON(
            "municipios",
            {clave: $('#entidadId').val()},
            function (data) {
                var html='';
                var len=data.length;
                for(var i=0;i<len;i++) {
                    html+='<option value="'+data[i].id+'">'+data[i].nombre+'</option>';
                }
                $('#municipioId').html(html);
            }
        );
     });

});
