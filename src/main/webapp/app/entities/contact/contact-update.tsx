import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { createEntity, getEntity, reset, updateEntity } from './contact.reducer';

export const ContactUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const contactEntity = useAppSelector(state => state.contact.entity);
  const loading = useAppSelector(state => state.contact.loading);
  const updating = useAppSelector(state => state.contact.updating);
  const updateSuccess = useAppSelector(state => state.contact.updateSuccess);

  const handleClose = () => {
    navigate('/contact');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...contactEntity,
      ...values,
      sender: videoUsers.find(it => it.id.toString() === values.sender?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...contactEntity,
          sender: contactEntity?.sender?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.contact.home.createOrEditLabel" data-cy="ContactCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.contact.home.createOrEditLabel">Create or edit a Contact</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="contact-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.message')}
                id="contact-message"
                name="message"
                data-cy="message"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.isContactViewed')}
                id="contact-isContactViewed"
                name="isContactViewed"
                data-cy="isContactViewed"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.isFollowed')}
                id="contact-isFollowed"
                name="isFollowed"
                data-cy="isFollowed"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.isDeleted')}
                id="contact-isDeleted"
                name="isDeleted"
                data-cy="isDeleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.isActive')}
                id="contact-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.createdBy')}
                id="contact-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.createdOn')}
                id="contact-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.updatedBy')}
                id="contact-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.contact.updatedOn')}
                id="contact-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="contact-sender"
                name="sender"
                data-cy="sender"
                label={translate('affliateMarketplaceApp.contact.sender')}
                type="select"
              >
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contact" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ContactUpdate;
