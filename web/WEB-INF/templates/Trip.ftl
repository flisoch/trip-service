<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">

    <script src="/static/js/trip.js" defer></script>
</#macro>


<#macro title>
    <title>Trip N</title>
</#macro>

<#macro content>
     <div class="container">
         <div class="row">

             <#if trip??>
                     <!-- CONTENT -->
                <div class="col">
                    <div class="container">
                        <div class="card text-center">
                            <div class="card-header">
                                Featured
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${trip.departurePoint}</h5>
                                <p class="card-text">
                                    Очень подробная информация тут
                                    Плоды томата отличаются высокими питательными, вкусовыми и диетическими качествами.
                                    Калорийность спелых плодов (энергетическая ценность) — 19 ккал. Они содержат 4,5—8,1 %
                                    сухого вещества, в котором половину представляют сахара, в основном глюкоза и фруктоза,
                                    а
                                    также органические кислоты (3,5—8,5 %), клетчатка (0,87—1,7 %)[4]. Плоды также содержат
                                    белки (0,6—1,1 %), пектиновые вещества (до 0,3 %), крахмал (0,07-0,3 %), минеральные
                                    вещества (0,6 %). В плодах томата высокое содержание каротиноидов (фитоен, неуроспорин,
                                    ликопин, неаликопин, каротин (0,8—1,2 мг/100 г сырой массы), ликосантин, ликофилл),
                                    витаминов (В1, В2, В3, В5), фолиевой и аскорбиновой кислоты (15—45 мг/100 г сырой
                                    массы),
                                    органических (лимонная, яблочная, щавелевая, винная, янтарная, гликолевая),
                                    высокомолекулярных жирных (пальмитиновая, стеариновая, линолевая) и фенолкарбоновых
                                    (п-кумаровая, кофейная, феруловая) кислот. В плодах найдены антоцианы, стеарины,
                                    тритерпеновые сапонины, абсцизировая кислота.


                                </p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                            <div class="card-footer text-muted">
                                ${trip.date}
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="container" id="comments-container">
                        <#if comments??>
                            <#list comments as comment>
                                <div class="card border-secondary mb-3">
                                    <div class="card-header">
                                        <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                    </div>
                                    <div class="card-body text-secondary">
                                        <h5 class="card-title">${comment.text}</h5>
                                        <p class="card-text">
                                            ${comment.date}
                                        </p>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                    <div class="container" id="comment-send">
                        <div class="card border-info mb-3">
                            <div class="card-header">Leave comment</div>
                            <textarea class="card-body text-secondary" id="comment-text"></textarea>
                            <div class="card-footer text-right">
                                <button class="btn btn-sm" onclick="sendComment(#{trip.id});">Send</button>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /CONTENT -->
             <#else>
                 takoy poezdki ne sushestvuet
             </#if>

         </div>
     </div>
</#macro>

<@display_page/>