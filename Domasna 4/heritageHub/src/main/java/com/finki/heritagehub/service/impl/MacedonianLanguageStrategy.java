package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageSelectionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MacedonianLanguageStrategy implements LanguageSelectionStrategy {
    private void setNavMacedonian(Model model){
        model.addAttribute("navHeader", "HeritageHub");
        model.addAttribute("navHome", "Почетна");
        model.addAttribute("navHistorical", "Историски");
        model.addAttribute("navCultural", "Културни");
        model.addAttribute("navAboutUs", "За нас");
        model.addAttribute("navLogIn", "Логирај се");
        model.addAttribute("navLogOut", "Одјава");
        model.addAttribute("navTooltip", "Емаил не е потврден");
    }
    @Override
    public void changeMonuments(Model model, HttpServletRequest request) {
        model.addAttribute("titleHistorical", "Историски Соменици");
        model.addAttribute("titleCultural", "Културни Споменици");
        setNavMacedonian(model);
        model.addAttribute("bodyHeaderHistorical", "Историски Соменици");
        model.addAttribute("bodyHeaderCultural", "Културни Споменици");
        model.addAttribute("bodySearchByName", "Пребарај по име");
        model.addAttribute("bodySearchByCity", "Пребарај по град");
        model.addAttribute("bodySearchButton", "Пребарај");
        model.addAttribute("bodyLatitude", "Географска ширина: ");
        model.addAttribute("bodyLongitude", "Географска должина: ");
        model.addAttribute("bodyCity", "Град: ");
        model.addAttribute("bodyRating", "Оцена: ");
        model.addAttribute("bodyView", "Погледни");
        model.addAttribute("bodyDelete", "Избриши");
    }

    @Override
    public void changeEditMonument(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Промени Споменик");
        setNavMacedonian(model);
        model.addAttribute("bodyHeader", "Измени споменик");
        changeAddEditForm(model);
    }
    @Override
    public void changeAddMonument(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Додади споменик");
        setNavMacedonian(model);
        model.addAttribute("bodyHeader", "Направи споменик");
        changeAddEditForm(model);
    }

    private void changeAddEditForm(Model model) {
        model.addAttribute("bodyLatitude", "Географска ширина:");
        model.addAttribute("bodyLongitude", "Географска должина:");
        model.addAttribute("bodyName", "Име:");
        model.addAttribute("bodyHistoric", "Историско");
        model.addAttribute("bodyCultural", "Културно");
        model.addAttribute("bodyCity", "Град:");
        model.addAttribute("bodyRating", "Оцена (0-5):");
        model.addAttribute("bodyNumRatings", "Број на оцени:");
        model.addAttribute("backButton", "Назад");
    }
    @Override
    public void changeAboutUs(Model model, HttpServletRequest request) {
        model.addAttribute("title", "За нас");
        setNavMacedonian(model);
        model.addAttribute("bodyHeader", "За нас");
        model.addAttribute("bodyTeamMembers", "Членови на тимот:");
        model.addAttribute("bodyTeamMember1", "Стефан Василев - 211021");
        model.addAttribute("bodyTeamMember2", "Андреј Генчовски - 211229");
        model.addAttribute("bodyTeamMember3", "Изабела Ефтимова - 211038");
        model.addAttribute("bodyTeamMember4", "Александар Сандев - 211213");
        model.addAttribute("bodyTeamMember5", "Исидора Бошкова - 211203");
        model.addAttribute("bodyMenotrs", "Ментори:");
        model.addAttribute("bodyMenotr1", "проф. д-р Љупчо Антовски");
        model.addAttribute("bodyMenotr2", "проф. д-р Петре Ламески");
        model.addAttribute("bodyContact", "Контакт");
    }

    @Override
    public void changeLogin(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Логирање");
        setNavMacedonian(model);
        model.addAttribute("bodyUsername", "Корисничко име:");
        model.addAttribute("bodyPassword", "Лозинка:");
        model.addAttribute("bodyLogIn", "Логирај се");
        model.addAttribute("bodyRegister", "Регистрирај се");
        model.addAttribute("backButton", "Назад");
    }

    @Override
    public void changeCategories(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Heritage Hub Почетна");
        setNavMacedonian(model);
        model.addAttribute("bodyHeader", "Запознај ги македонските културно историски локации");
        model.addAttribute("bodyCulturalMonuments", "Култирни Споменици");
        model.addAttribute("bodyHistoricalMonuments", "Историски Споменици");
        model.addAttribute("bodyAddMonument", "Додади споменик");
    }

    @Override
    public void changeMonumentDetails(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Детали");
        setNavMacedonian(model);
        model.addAttribute("bodyYourRating", "Твојата оцена: ");
        model.addAttribute("bodyRateThisMonument", "Оцени го овој споменик");
        model.addAttribute("bodyRating", "Оцена (0-5): ");
        model.addAttribute("bodySubmitButton", "Потврди");
        model.addAttribute("backButton", "Назад");
    }

    @Override
    public void changeRegister(Model model, HttpServletRequest request) {
        changeLogin(model, request);
        model.addAttribute("bodyEmail", "Е-маил:");
    }

    @Override
    public void changeRegisterConfirmation(Model model, HttpServletRequest request) {
        setNavMacedonian(model);
        model.addAttribute("title", "Успешна Регистрација");
        model.addAttribute("bodyRegistrationSuccessful","Успешна Регистрација");
        model.addAttribute("bodyRegisterMessage", "За да ги отклучите сите кориснички функционалности, потвредете го вашиот емаил.");
        model.addAttribute("bodyLogIn", "Логирајте се");
        model.addAttribute("bodyContinueAsGuest", "Продолжете како гостин");
    }
}

