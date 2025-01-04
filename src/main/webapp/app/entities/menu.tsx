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
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
