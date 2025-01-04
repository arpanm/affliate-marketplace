import React from 'react';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/video-user">
        <Translate contentKey="global.menu.entities.videoUser" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/bank-details">
        <Translate contentKey="global.menu.entities.bankDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contact">
        <Translate contentKey="global.menu.entities.contact" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/video-post">
        <Translate contentKey="global.menu.entities.videoPost" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/video-tag">
        <Translate contentKey="global.menu.entities.videoTag" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/sponsor">
        <Translate contentKey="global.menu.entities.sponsor" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/competition">
        <Translate contentKey="global.menu.entities.competition" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/prize">
        <Translate contentKey="global.menu.entities.prize" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/competition-winner">
        <Translate contentKey="global.menu.entities.competitionWinner" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/competition-payment-from-sponsor">
        <Translate contentKey="global.menu.entities.competitionPaymentFromSponsor" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/competition-payment-to-winner">
        <Translate contentKey="global.menu.entities.competitionPaymentToWinner" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/review">
        <Translate contentKey="global.menu.entities.review" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/affinity">
        <Translate contentKey="global.menu.entities.affinity" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/video-post-change-history">
        <Translate contentKey="global.menu.entities.videoPostChangeHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/review-change-history">
        <Translate contentKey="global.menu.entities.reviewChangeHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ai-tool">
        <Translate contentKey="global.menu.entities.aiTool" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ai-tool-session">
        <Translate contentKey="global.menu.entities.aiToolSession" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ai-tool-chat">
        <Translate contentKey="global.menu.entities.aiToolChat" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ai-tool-payment">
        <Translate contentKey="global.menu.entities.aiToolPayment" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
